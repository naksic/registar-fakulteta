import { useEffect, useState } from "react";
import fakultetApi from "../api/fakultetApi";
import univerzitetApi from "../api/univerzitetApi";
import FakultetForma from "../components/FakultetForma";

function FakultetiPage() {
  const [fakulteti, setFakulteti] = useState([]);
  const [univerziteti, setUniverziteti] = useState([]);
  const [greska, setGreska] = useState(null);
  const [ucitavanje, setUcitavanje] = useState(true);

  const [prikaziFormu, setPrikaziFormu] = useState(false);
  const [fakultetZaIzmenu, setFakultetZaIzmenu] = useState(null);


  const [filterNaziv, setFilterNaziv] = useState("");
  const [filterSkraceniNaziv, setFilterSkraceniNaziv] = useState("");
  const [filterUniverzitetId, setFilterUniverzitetId] = useState("");
  const [filterStatus, setFilterStatus] = useState("");
  const [pretragaAktivna, setPretragaAktivna] = useState(false);

  const ucitajPodatke = () => {
    setUcitavanje(true);
    Promise.all([fakultetApi.getAll(), univerzitetApi.getAll()])
      .then(([fakultetiRes, univerzitetiRes]) => {
        setFakulteti(fakultetiRes.data);
        setUniverziteti(univerzitetiRes.data);
      })
      .catch((error) => setGreska(error.message))
      .finally(() => setUcitavanje(false));
  };

  useEffect(() => {
    ucitajPodatke();
  }, []);

  const obrisiFakultet = (id) => {
    if (!window.confirm("Da li si siguran da želiš da obrišeš ovaj fakultet? \nUPOZORENJE: Ovo će obrisati i sve njegove organizacione jedinice i mandate uprave (sa članovima uprave)."))
      return;

    setGreska(null);

    fakultetApi
      .delete(id)
      .then(() => ucitajPodatke())
      .catch((error) => setGreska(error.message));
  };

  const otvoriFormuZaDodavanje = () => {
    setFakultetZaIzmenu(null);
    setPrikaziFormu(true);
  };

  const otvoriFormuZaIzmenu = (fakultet) => {
    setFakultetZaIzmenu(fakultet);
    setPrikaziFormu(true);
  };

  const zatvoriFormu = () => {
    setGreska(null);
    setPrikaziFormu(false);
    setFakultetZaIzmenu(null);
  };

  const sacuvajFakultet = (podaci) => {
    const zahtev = fakultetZaIzmenu
      ? fakultetApi.update(fakultetZaIzmenu.id, podaci)
      : fakultetApi.create(podaci);

    zahtev
      .then(() => {
        zatvoriFormu();
        ucitajPodatke();
      })
      .catch((error) => setGreska(error.message));
  };

  const pretraziFakultete = (e) => {
    e.preventDefault();

    const kriterijumi = {};
    if (filterNaziv.trim()) kriterijumi.naziv = filterNaziv.trim();
    if (filterSkraceniNaziv.trim())
      kriterijumi.skraceniNaziv = filterSkraceniNaziv.trim();
    if (filterUniverzitetId) kriterijumi.univerzitetId = filterUniverzitetId;
    if (filterStatus) kriterijumi.status = filterStatus;

    if (Object.keys(kriterijumi).length === 0) {
      ocistiPretragu();
      return;
    }

    setUcitavanje(true);
    fakultetApi
      .pretraga(kriterijumi)
      .then((response) => {
        setFakulteti(response.data);
        setPretragaAktivna(true);
      })
      .catch((error) => setGreska(error.message))
      .finally(() => setUcitavanje(false));
  };

  const ocistiPretragu = () => {
    setFilterNaziv("");
    setFilterSkraceniNaziv("");
    setFilterUniverzitetId("");
    setFilterStatus("");
    setPretragaAktivna(false);
    ucitajPodatke();
  };

  const preuzmiExcel = () => {
    fakultetApi
      .exportExcel()
      .then((response) => {
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement("a");
        link.href = url;
        link.setAttribute("download", "fakulteti.xlsx");
        document.body.appendChild(link);
        link.click();
        link.remove();
        window.URL.revokeObjectURL(url);
      })
      .catch((error) =>
        setGreska(error.response?.data?.message || error.message)
      );
  };

  if (ucitavanje) return <div className="container mt-4">Učitavanje...</div>;

  return (
    <div className="container mt-4">
      <h1>Fakulteti</h1>

      <form
        onSubmit={pretraziFakultete}
        className="p-3 border rounded mb-3 bg-light"
      >
        <h6>Pretraga po kriterijumima</h6>
        <div className="row g-2">
          <div className="col-md-3">
            <input
              className="form-control"
              placeholder="Naziv..."
              value={filterNaziv}
              onChange={(e) => setFilterNaziv(e.target.value)}
            />
          </div>
          <div className="col-md-3">
            <input
              className="form-control"
              placeholder="Skraćeni naziv..."
              value={filterSkraceniNaziv}
              onChange={(e) => setFilterSkraceniNaziv(e.target.value)}
            />
          </div>
          <div className="col-md-3">
            <select
              className="form-select"
              value={filterUniverzitetId}
              onChange={(e) => setFilterUniverzitetId(e.target.value)}
            >
              <option value="">Svi univerziteti</option>
              {univerziteti.map((u) => (
                <option key={u.id} value={u.id}>
                  {u.naziv}
                </option>
              ))}
            </select>
          </div>
          <div className="col-md-3">
            <select
              className="form-select"
              value={filterStatus}
              onChange={(e) => setFilterStatus(e.target.value)}
            >
              <option value="">Svi statusi</option>
              <option value="AKTIVAN">AKTIVAN</option>
              <option value="NEAKTIVAN">NEAKTIVAN</option>
              <option value="UGASEN">UGAŠEN</option>
            </select>
          </div>
        </div>

        <div className="mt-2 d-flex gap-2">
          <button type="submit" className="btn btn-outline-primary">
            Pretraži
          </button>
          <button
            type="button"
            className="btn btn-outline-secondary"
            onClick={ocistiPretragu}
            disabled={!pretragaAktivna}
          >
            Poništi
          </button>
        </div>
      </form>

      {greska && <p className="text-danger">Greška: {greska}</p>}

      {prikaziFormu ? (
        <FakultetForma
          postojeciFakultet={fakultetZaIzmenu}
          univerziteti={univerziteti}
          onSacuvaj={sacuvajFakultet}
          onOtkazi={zatvoriFormu}
        />
      ) : (
        <div className="d-flex gap-2 mb-3">
          <button className="btn btn-primary" onClick={otvoriFormuZaDodavanje}>
            Dodaj novi fakultet
          </button>
          <button className="btn btn-success" onClick={preuzmiExcel}>
            Izvezi u Excel
          </button>
        </div>
      )}

      

      {pretragaAktivna && (
        <p className="text-muted">
          Pronađeno fakulteta: {fakulteti.length}
        </p>
      )}

      <table className="table table-striped mt-3">
        <thead>
          <tr>
            <th>ID</th>
            <th>Naziv</th>
            <th>Skraćeni naziv</th>
            <th>Univerzitet</th>
            <th>Status</th>
            <th>Website</th>
            <th>Akcije</th>
          </tr>
        </thead>
        <tbody>
          {fakulteti.map((f) => (
            <tr key={f.id}>
              <td>{f.id}</td>
              <td>{f.naziv}</td>
              <td>{f.skraceniNaziv}</td>
              <td>{f.nazivUniverziteta}</td>
              <td>{f.status}</td>
              <td>{f.website}</td>
              <td>
                <button
                  className="btn btn-outline-primary btn-sm me-2"
                  onClick={() => otvoriFormuZaIzmenu(f)}
                >
                  Izmeni
                </button>
                <button
                  className="btn btn-danger btn-sm"
                  onClick={() => obrisiFakultet(f.id)}
                >
                  Obriši
                </button>
              </td>
            </tr>
          ))}
          {fakulteti.length === 0 && (
            <tr>
              <td colSpan="7" className="text-center text-muted">
                Nema fakulteta koji zadovoljavaju zadate kriterijume.
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default FakultetiPage;