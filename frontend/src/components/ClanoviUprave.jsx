import { useState, useEffect } from "react";
import clanUpraveApi from "../api/clanUpraveApi";

const funkcije = [
  "DEKAN",
  "PRODEKAN_ZA_NASTAVU",
  "PRODEKAN_ZA_NAUKU",
  "PRODEKAN_ZA_FINANSIJE",
  "PRODEKAN_STUDENT",
  "SEKRETAR",
];

function dozvoljenaLicaZaFunkciju(
  funkcija,
  nastavnoOsoblje,
  administrativnoOsoblje,
  studenti,
) {
  if (funkcija === "DEKAN") {
    return nastavnoOsoblje.filter((o) => o.zvanje === "REDOVNI_PROFESOR");
  }
  if (funkcija === "SEKRETAR") {
    return administrativnoOsoblje;
  }
  if (funkcija === "PRODEKAN_STUDENT") {
    return studenti;
  }
  return nastavnoOsoblje;
}

function ClanoviUprave({
  mandatId,
  nastavnoOsoblje,
  administrativnoOsoblje,
  studenti,
}) {
  const [clanovi, setClanovi] = useState([]);
  const [greska, setGreska] = useState(null);
  const [ucitavanje, setUcitavanje] = useState(true);

  const [noviLiceId, setNoviLiceId] = useState("");
  const [novaFunkcija, setNovaFunkcija] = useState("DEKAN");

  const ucitajClanove = () => {
    setUcitavanje(true);
    clanUpraveApi
      .getByMandatId(mandatId)
      .then((response) => setClanovi(response.data))
      .catch((error) => setGreska(error.message))
      .finally(() => setUcitavanje(false));
  };

  useEffect(() => {
    ucitajClanove();
  }, [mandatId]);

  useEffect(() => {
    if (dostupneFunkcije.length > 0 && !dostupneFunkcije.includes(novaFunkcija)) {
      setNovaFunkcija(dostupneFunkcije[0]);
    }
  }, [clanovi]);

  const zauzeteFunkcije = clanovi.map((c) => c.funkcija);
  const dostupneFunkcije = funkcije.filter((f) => !zauzeteFunkcije.includes(f));
  const zauzetaLica = clanovi.map((c) => c.liceId);

  const dozvoljenaLica = dozvoljenaLicaZaFunkciju(novaFunkcija, nastavnoOsoblje, administrativnoOsoblje, studenti).filter((l) => !zauzetaLica.includes(l.id));

  const promeniFunkciju = (e) => {
    setNovaFunkcija(e.target.value);
    setNoviLiceId("");
  };

  const dodajClana = (e) => {
    e.preventDefault();
    if (!noviLiceId) return;

    clanUpraveApi
      .create({
        mandatUpraveId: mandatId,
        liceId: Number(noviLiceId),
        funkcija: novaFunkcija,
      })
      .then(() => {
        setNoviLiceId("");
        ucitajClanove();
      })
      .catch((error) =>
        setGreska(error.response?.data?.poruka || error.message),
      );
  };

  const obrisiClana = (id) => {
    if (
      !window.confirm("Da li si siguran da želiš da ukloniš ovog člana uprave?")
    )
      return;

    clanUpraveApi
      .delete(id)
      .then(() => ucitajClanove())
      .catch((error) => setGreska(error.message));
  };

  return (
    <div className="p-3 bg-light border rounded mb-3">
      <h5>Članovi uprave</h5>

      {greska && <p className="text-danger">Greška: {greska}</p>}

      {dostupneFunkcije.length === 0 ? (
        <p className="text-muted">
          Sve funkcije su već popunjene za ovaj mandat.
        </p>
      ) : (

      <form onSubmit={dodajClana} className="d-flex gap-2 mb-3 align-items-end">
        <div>
          <label className="form-label">Funkcija</label>
          <select
            className="form-select"
            value={novaFunkcija}
            onChange={promeniFunkciju}
          >
            {dostupneFunkcije.map((f) => (
              <option key={f} value={f}>
                {f}
              </option>
            ))}
          </select>
        </div>

        <div>
          <label className="form-label">Lice</label>
          <select
            className="form-select"
            value={noviLiceId}
            onChange={(e) => setNoviLiceId(e.target.value)}
            required
          >
            <option value="">-- Izaberi lice --</option>
            {dozvoljenaLica.map((l) => (
              <option key={l.id} value={l.id}>
                {l.ime} {l.prezime}
              </option>
            ))}
          </select>
          {dozvoljenaLica.length === 0 && (
            <small className="text-muted d-block mt-1">
              Nema dostupnih lica za ovu funkciju.
            </small>
          )}
        </div>

        <button type="submit" className="btn btn-primary">
          Dodaj člana
        </button>
      </form>
      )}

      {ucitavanje ? (
        <p>Učitavanje...</p>
      ) : clanovi.length === 0 ? (
        <p className="text-muted">Ovaj mandat još nema dodeljenih članova.</p>
      ) : (
        <table className="table table-sm bg-white">
          <thead>
            <tr>
              <th>Ime i prezime</th>
              <th>Funkcija</th>
              <th>Akcije</th>
            </tr>
          </thead>
          <tbody>
            {clanovi.map((c) => (
              <tr key={c.id}>
                <td>{c.imePrezimeLica}</td>
                <td>{c.funkcija}</td>
                <td>
                  <button
                    className="btn btn-danger btn-sm"
                    onClick={() => obrisiClana(c.id)}
                  >
                    Ukloni
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default ClanoviUprave;
