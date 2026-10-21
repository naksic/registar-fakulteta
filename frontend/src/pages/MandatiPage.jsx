import { useEffect, useState } from "react";
import mandatUpraveApi from "../api/mandatUpraveApi";
import fakultetApi from "../api/fakultetApi";
import nastavnoOsobljeApi from "../api/nastavnoOsobljeApi";
import administrativnoOsobljeApi from "../api/administrativnoOsobljeApi";
import studentApi from "../api/studentApi";
import MandatForma from "../components/MandatForma";
import ClanoviUprave from "../components/ClanoviUprave";

function MandatiPage() {
  const [mandati, setMandati] = useState([]);
  const [fakulteti, setFakulteti] = useState([]);
  const [nastavnoOsoblje, setNastavnoOsoblje] = useState([]);
  const [administrativnoOsoblje, setAdministrativnoOsoblje] = useState([]);
  const [studenti, setStudenti] = useState([]);
  const [greska, setGreska] = useState(null);
  const [ucitavanje, setUcitavanje] = useState(true);

  const [prikaziFormu, setPrikaziFormu] = useState(false);
  const [mandatZaIzmenu, setMandatZaIzmenu] = useState(null);
  const [prikazaniMandatId, setPrikazaniMandatId] = useState(null);

  const ucitajPodatke = () => {
    setUcitavanje(true);
    Promise.all([
      mandatUpraveApi.getAll(),
      fakultetApi.getAll(),
      nastavnoOsobljeApi.getAll(),
      administrativnoOsobljeApi.getAll(),
      studentApi.getAll(),
    ])
      .then(
        ([
          mandatiRes,
          fakultetiRes,
          nastavnoRes,
          administrativnoRes,
          studentiRes,
        ]) => {
          setMandati(mandatiRes.data);
          setFakulteti(fakultetiRes.data);
          setNastavnoOsoblje(nastavnoRes.data);
          setAdministrativnoOsoblje(administrativnoRes.data);
          setStudenti(studentiRes.data);
        },
      )
      .catch((error) => setGreska(error.message))
      .finally(() => setUcitavanje(false));
  };

  useEffect(() => {
    ucitajPodatke();
  }, []);

  const obrisiMandat = (id) => {
    if (
      !window.confirm(
        "Da li si siguran da želiš da obrišeš ovaj mandat? Ovo će obrisati i sve njegove članove uprave.",
      )
    )
      return;

    mandatUpraveApi
      .delete(id)
      .then(() => ucitajPodatke())
      .catch((error) => setGreska(error.message));
  };

  const otvoriFormuZaDodavanje = () => {
    setMandatZaIzmenu(null);
    setPrikaziFormu(true);
  };

  const otvoriFormuZaIzmenu = (mandat) => {
    setMandatZaIzmenu(mandat);
    setPrikaziFormu(true);
  };

  const zatvoriFormu = () => {
    setPrikaziFormu(false);
    setMandatZaIzmenu(null);
  };

  const sacuvajMandat = (podaci) => {
    const zahtev = mandatZaIzmenu
      ? mandatUpraveApi.update(mandatZaIzmenu.id, podaci)
      : mandatUpraveApi.create(podaci);

    zahtev
      .then(() => {
        zatvoriFormu();
        ucitajPodatke();
      })
      .catch((error) => setGreska(error.message));
  };

  const prikaziClanove = (mandatId) => {
    setPrikazaniMandatId(prikazaniMandatId === mandatId ? null : mandatId);
  };

  if (ucitavanje) return <div className="container mt-4">Učitavanje...</div>;

  return (
    <div className="container mt-4">
      <h1>Mandati uprave</h1>

      {greska && <p className="text-danger">Greška: {greska}</p>}

      {prikaziFormu ? (
        <MandatForma
          postojeciMandat={mandatZaIzmenu}
          fakulteti={fakulteti}
          onSacuvaj={sacuvajMandat}
          onOtkazi={zatvoriFormu}
        />
      ) : (
        <button
          className="btn btn-primary mb-3"
          onClick={otvoriFormuZaDodavanje}
        >
          Dodaj novi mandat
        </button>
      )}

      <table className="table table-striped mt-3">
        <thead>
          <tr>
            <th>ID</th>
            <th>Fakultet</th>
            <th>Datum početka</th>
            <th>Datum završetka</th>
            <th>Status</th>
            <th>Akcije</th>
          </tr>
        </thead>
        <tbody>
          {mandati.map((m) => (
            <tr key={m.id}>
              <td>{m.id}</td>
              <td>{m.nazivFakulteta}</td>
              <td>{m.datumPocetka}</td>
              <td>{m.datumZavrsetka || "—"}</td>
              <td>{m.datumZavrsetka ? "Bivši" : "Aktivan"}</td>
              <td>
                <button
                  className="btn btn-outline-secondary btn-sm me-2"
                  onClick={() => prikaziClanove(m.id)}
                >
                  {prikazaniMandatId === m.id
                    ? "Sakrij članove"
                    : "Članovi uprave"}
                </button>
                <button
                  className="btn btn-outline-primary btn-sm me-2"
                  onClick={() => otvoriFormuZaIzmenu(m)}
                >
                  Izmeni
                </button>
                <button
                  className="btn btn-danger btn-sm"
                  onClick={() => obrisiMandat(m.id)}
                >
                  Obriši
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {prikazaniMandatId && (
        <ClanoviUprave
          mandatId={prikazaniMandatId}
          nastavnoOsoblje={nastavnoOsoblje}
          administrativnoOsoblje={administrativnoOsoblje}
          studenti={studenti}
        />
      )}
    </div>
  );
}

export default MandatiPage;
