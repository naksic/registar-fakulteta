import { useEffect, useState } from "react";
import organizacionaJedinicaApi from "../api/organizacionaJedinicaApi";
import fakultetApi from "../api/fakultetApi";
import OrganizacionaJedinicaForma from "../components/OrganizacionaJedinicaForma";

function OrganizacioneJedinicePage() {
  const [jedinice, setJedinice] = useState([]);
  const [fakulteti, setFakulteti] = useState([]);
  const [greska, setGreska] = useState(null);
  const [ucitavanje, setUcitavanje] = useState(true);

  const [prikaziFormu, setPrikaziFormu] = useState(false);
  const [jedinicaZaIzmenu, setJedinicaZaIzmenu] = useState(null);

  const ucitajPodatke = () => {
    setUcitavanje(true);
    Promise.all([organizacionaJedinicaApi.getAll(), fakultetApi.getAll()])
      .then(([jediniceRes, fakultetiRes]) => {
        setJedinice(jediniceRes.data);
        setFakulteti(fakultetiRes.data);
      })
      .catch((error) => setGreska(error.message))
      .finally(() => setUcitavanje(false));
  };

  useEffect(() => {
    ucitajPodatke();
  }, []);

  const obrisiJedinicu = (id) => {
    if (!window.confirm("Da li si siguran da želiš da obrišeš ovu organizacionu jedinicu?",))
      return;

    setGreska(null);

    organizacionaJedinicaApi
      .delete(id)
      .then(() => ucitajPodatke())
      .catch((error) => setGreska(error.message));
  };

  const otvoriFormuZaDodavanje = () => {
    setJedinicaZaIzmenu(null);
    setPrikaziFormu(true);
  };

  const otvoriFormuZaIzmenu = (jedinica) => {
    setJedinicaZaIzmenu(jedinica);
    setPrikaziFormu(true);
  };

  const zatvoriFormu = () => {
    setGreska(null);
    setPrikaziFormu(false);
    setJedinicaZaIzmenu(null);
  };

  const sacuvajJedinicu = (podaci) => {
    const zahtev = jedinicaZaIzmenu
      ? organizacionaJedinicaApi.update(jedinicaZaIzmenu.id, podaci)
      : organizacionaJedinicaApi.create(podaci);

    zahtev
      .then(() => {
        zatvoriFormu();
        ucitajPodatke();
      })
      .catch((error) => setGreska(error.message));
  };

  if (ucitavanje) return <div className="container mt-4">Učitavanje...</div>;

  return (
    <div className="container mt-4">
      <h1>Organizacione jedinice</h1>

      {greska && <p className="text-danger">Greška: {greska}</p>}

      {prikaziFormu ? (
        <OrganizacionaJedinicaForma
          postojecaJedinica={jedinicaZaIzmenu}
          fakulteti={fakulteti}
          onSacuvaj={sacuvajJedinicu}
          onOtkazi={zatvoriFormu}
        />
      ) : (
        <button
          className="btn btn-primary mb-3"
          onClick={otvoriFormuZaDodavanje}
        >
          Dodaj novu organizacionu jedinicu
        </button>
      )}

      <table className="table table-striped mt-3">
        <thead>
          <tr>
            <th>ID</th>
            <th>Naziv</th>
            <th>Fakultet</th>
            <th>Akcije</th>
          </tr>
        </thead>
        <tbody>
          {jedinice.map((j) => (
            <tr key={j.id}>
              <td>{j.id}</td>
              <td>{j.naziv}</td>
              <td>{j.nazivFakulteta}</td>
              <td>
                <button
                  className="btn btn-outline-primary btn-sm me-2"
                  onClick={() => otvoriFormuZaIzmenu(j)}
                >
                  Izmeni
                </button>
                <button
                  className="btn btn-danger btn-sm"
                  onClick={() => obrisiJedinicu(j.id)}
                >
                  Obriši
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default OrganizacioneJedinicePage;
