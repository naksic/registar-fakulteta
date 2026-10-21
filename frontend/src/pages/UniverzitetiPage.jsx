import { useEffect, useState } from "react";
import univerzitetApi from "../api/univerzitetApi";
import UniverzitetForma from "../components/UniverzitetForma";

function UniverzitetiPage() {
  const [univerziteti, setUniverziteti] = useState([]);
  const [greska, setGreska] = useState(null);
  const [ucitavanje, setUcitavanje] = useState(true);

  const [prikaziFormu, setPrikaziFormu] = useState(false);
  const [univerzitetZaIzmenu, setUniverzitetZaIzmenu] = useState(null);

  const ucitajUniverzitete = () => {
    setUcitavanje(true);
    univerzitetApi
      .getAll()
      .then((response) => setUniverziteti(response.data))
      .catch((error) => setGreska(error.message))
      .finally(() => setUcitavanje(false));
  };

  useEffect(() => {
    ucitajUniverzitete();
  }, []);

  const obrisiUniverzitet = (id) => {
    if (
      !window.confirm("Da li si siguran da želiš da obrišeš ovaj univerzitet?")
    )
      return;

    univerzitetApi
      .delete(id)
      .then(() => ucitajUniverzitete())
      .catch((error) => setGreska(error.message));
  };

  const otvoriFormuZaDodavanje = () => {
    setUniverzitetZaIzmenu(null);
    setPrikaziFormu(true);
  };

  const otvoriFormuZaIzmenu = (univerzitet) => {
    setUniverzitetZaIzmenu(univerzitet);
    setPrikaziFormu(true);
  };

  const zatvoriFormu = () => {
    setPrikaziFormu(false);
    setUniverzitetZaIzmenu(null);
  };

  const sacuvajUniverzitet = (podaci) => {
    const zahtev = univerzitetZaIzmenu
      ? univerzitetApi.update(univerzitetZaIzmenu.id, podaci)
      : univerzitetApi.create(podaci);

    zahtev
      .then(() => {
        zatvoriFormu();
        ucitajUniverzitete();
      })
      .catch((error) => setGreska(error.message));
  };

  if (ucitavanje) return <div className="container mt-4">Učitavanje...</div>;

  return (
    <div className="container mt-4">
      <h1>Univerziteti</h1>

      {greska && <p className="text-danger">Greška: {greska}</p>}

      {prikaziFormu ? (
        <UniverzitetForma
          postojeciUniverzitet={univerzitetZaIzmenu}
          onSacuvaj={sacuvajUniverzitet}
          onOtkazi={zatvoriFormu}
        />
      ) : (
        <button
          className="btn btn-primary mb-3"
          onClick={otvoriFormuZaDodavanje}
        >
          Dodaj novi univerzitet
        </button>
      )}

      <table className="table table-striped mt-3">
        <thead>
          <tr>
            <th>ID</th>
            <th>Naziv</th>
            <th>Adresa</th>
            <th>Email</th>
            <th>Telefon</th>
            <th>Godina osnivanja</th>
            <th>Website</th>
            <th>Akcije</th>
          </tr>
        </thead>
        <tbody>
          {univerziteti.map((u) => (
            <tr key={u.id}>
              <td>{u.id}</td>
              <td>{u.naziv}</td>
              <td>{u.adresa}</td>
              <td>{u.email}</td>
              <td>{u.telefon}</td>
              <td>{u.godinaOsnivanja}</td>
              <td>{u.website}</td>
              <td>
                <button
                  className="btn btn-outline-primary btn-sm me-2"
                  onClick={() => otvoriFormuZaIzmenu(u)}
                >
                  Izmeni
                </button>
                <button
                  className="btn btn-danger btn-sm"
                  onClick={() => obrisiUniverzitet(u.id)}
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

export default UniverzitetiPage;
