import { useEffect, useState } from "react";
import nastavnoOsobljeApi from "../api/nastavnoOsobljeApi";
import administrativnoOsobljeApi from "../api/administrativnoOsobljeApi";
import studentApi from "../api/studentApi";
import liceApi from "../api/liceApi";
import NastavnoOsobljeForma from "../components/NastavnoOsobljeForma";
import AdministrativnoOsobljeForma from "../components/AdministrativnoOsobljeForma";
import StudentForma from "../components/StudentForma";

function LicaPage() {
  const [aktivniTab, setAktivniTab] = useState("nastavno");

  return (
    <div className="container mt-4">
      <h1>Lica</h1>

      <ul className="nav nav-tabs mb-3">
        <li className="nav-item">
          <button
            className={`nav-link ${aktivniTab === "nastavno" ? "active" : ""}`}
            onClick={() => setAktivniTab("nastavno")}
          >
            Nastavno osoblje
          </button>
        </li>
        <li className="nav-item">
          <button
            className={`nav-link ${aktivniTab === "administrativno" ? "active" : ""}`}
            onClick={() => setAktivniTab("administrativno")}
          >
            Administrativno osoblje
          </button>
        </li>
        <li className="nav-item">
          <button
            className={`nav-link ${aktivniTab === "studenti" ? "active" : ""}`}
            onClick={() => setAktivniTab("studenti")}
          >
            Studenti
          </button>
        </li>
        <li className="nav-item">
          <button
            className={`nav-link ${aktivniTab === "pretraga" ? "active" : ""}`}
            onClick={() => setAktivniTab("pretraga")}
          >
            Pretraga
          </button>
        </li>
      </ul>

      {aktivniTab === "nastavno" && <NastavnoOsobljeTab />}
      {aktivniTab === "administrativno" && <AdministrativnoOsobljeTab />}
      {aktivniTab === "studenti" && <StudentiTab />}
      {aktivniTab === "pretraga" && <PretragaTab />}
    </div>
  );
}

function NastavnoOsobljeTab() {
  const [lista, setLista] = useState([]);
  const [greska, setGreska] = useState(null);
  const [ucitavanje, setUcitavanje] = useState(true);
  const [prikaziFormu, setPrikaziFormu] = useState(false);
  const [zaIzmenu, setZaIzmenu] = useState(null);

  const ucitaj = () => {
    setUcitavanje(true);
    nastavnoOsobljeApi
      .getAll()
      .then((response) => setLista(response.data))
      .catch((error) => setGreska(error.message))
      .finally(() => setUcitavanje(false));
  };

  useEffect(() => {
    ucitaj();
  }, []);

  const obrisi = (id) => {
    if (!window.confirm("Da li si siguran da želiš da obrišeš ovog zaposlenog?") )
      return;

    setGreska(null);

    nastavnoOsobljeApi
      .delete(id)
      .then(() => ucitaj())
      .catch((error) => setGreska(error.message));
  };

  const sacuvaj = (podaci) => {
    const zahtev = zaIzmenu
      ? nastavnoOsobljeApi.update(zaIzmenu.id, podaci)
      : nastavnoOsobljeApi.create(podaci);
    zahtev
      .then(() => {
        setPrikaziFormu(false);
        setZaIzmenu(null);
        ucitaj();
      })
      .catch((error) => setGreska(error.message));
  };

  if (ucitavanje) return <div>Učitavanje...</div>;

  return (
    <div>
      {greska && <p className="text-danger">Greška: {greska}</p>}

      {prikaziFormu ? (
        <NastavnoOsobljeForma
          postojeciZaposleni={zaIzmenu}
          onSacuvaj={sacuvaj}
          onOtkazi={() => {
            setGreska(null);
            setPrikaziFormu(false);
            setZaIzmenu(null);
          }}
        />
      ) : (
        <button
          className="btn btn-primary mb-3"
          onClick={() => {
            setGreska(null);
            setZaIzmenu(null);
            setPrikaziFormu(true);
          }}
        >
          Dodaj novo nastavno osoblje
        </button>
      )}

      <table className="table table-striped">
        <thead>
          <tr>
            <th>ID</th>
            <th>Ime</th>
            <th>Prezime</th>
            <th>Uža oblast</th>
            <th>Zvanje</th>
            <th>Akcije</th>
          </tr>
        </thead>
        <tbody>
          {lista.map((o) => (
            <tr key={o.id}>
              <td>{o.id}</td>
              <td>{o.ime}</td>
              <td>{o.prezime}</td>
              <td>{o.uzaOblast}</td>
              <td>{o.zvanje}</td>
              <td>
                <button
                  className="btn btn-outline-primary btn-sm me-2"
                  onClick={() => {
                    setZaIzmenu(o);
                    setPrikaziFormu(true);
                  }}
                >
                  Izmeni
                </button>
                <button
                  className="btn btn-danger btn-sm"
                  onClick={() => obrisi(o.id)}
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

function AdministrativnoOsobljeTab() {
  const [lista, setLista] = useState([]);
  const [greska, setGreska] = useState(null);
  const [ucitavanje, setUcitavanje] = useState(true);
  const [prikaziFormu, setPrikaziFormu] = useState(false);
  const [zaIzmenu, setZaIzmenu] = useState(null);

  const ucitaj = () => {
    setUcitavanje(true);
    administrativnoOsobljeApi
      .getAll()
      .then((response) => setLista(response.data))
      .catch((error) => setGreska(error.message))
      .finally(() => setUcitavanje(false));
  };

  useEffect(() => {
    ucitaj();
  }, []);

  const obrisi = (id) => {
    if (!window.confirm("Da li si siguran da želiš da obrišeš ovog zaposlenog?"))
      return;

    setGreska(null);

    administrativnoOsobljeApi
      .delete(id)
      .then(() => ucitaj())
      .catch((error) => setGreska(error.message));
  };

  const sacuvaj = (podaci) => {
    const zahtev = zaIzmenu
      ? administrativnoOsobljeApi.update(zaIzmenu.id, podaci)
      : administrativnoOsobljeApi.create(podaci);
    zahtev
      .then(() => {
        setPrikaziFormu(false);
        setZaIzmenu(null);
        ucitaj();
      })
      .catch((error) => setGreska(error.message));
  };

  if (ucitavanje) return <div>Učitavanje...</div>;

  return (
    <div>
      {greska && <p className="text-danger">Greška: {greska}</p>}

      {prikaziFormu ? (
        <AdministrativnoOsobljeForma
          postojeciZaposleni={zaIzmenu}
          onSacuvaj={sacuvaj}
          onOtkazi={() => {
            setGreska(null);
            setPrikaziFormu(false);
            setZaIzmenu(null);
          }}
        />
      ) : (
        <button
          className="btn btn-primary mb-3"
          onClick={() => {
            setGreska(null);
            setZaIzmenu(null);
            setPrikaziFormu(true);
          }}
        >
          Dodaj novo administrativno osoblje
        </button>
      )}

      <table className="table table-striped">
        <thead>
          <tr>
            <th>ID</th>
            <th>Ime</th>
            <th>Prezime</th>
            <th>Radno mesto</th>
            <th>Sektor</th>
            <th>Akcije</th>
          </tr>
        </thead>
        <tbody>
          {lista.map((o) => (
            <tr key={o.id}>
              <td>{o.id}</td>
              <td>{o.ime}</td>
              <td>{o.prezime}</td>
              <td>{o.radnoMesto}</td>
              <td>{o.sektor}</td>
              <td>
                <button
                  className="btn btn-outline-primary btn-sm me-2"
                  onClick={() => {
                    setZaIzmenu(o);
                    setPrikaziFormu(true);
                  }}
                >
                  Izmeni
                </button>
                <button
                  className="btn btn-danger btn-sm"
                  onClick={() => obrisi(o.id)}
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

function StudentiTab() {
  const [lista, setLista] = useState([]);
  const [greska, setGreska] = useState(null);
  const [ucitavanje, setUcitavanje] = useState(true);
  const [prikaziFormu, setPrikaziFormu] = useState(false);
  const [zaIzmenu, setZaIzmenu] = useState(null);

  const ucitaj = () => {
    setUcitavanje(true);
    studentApi
      .getAll()
      .then((response) => setLista(response.data))
      .catch((error) => setGreska(error.message))
      .finally(() => setUcitavanje(false));
  };

  useEffect(() => {
    ucitaj();
  }, []);

  const obrisi = (id) => {
    if(!window.confirm("Da li si siguran da želiš da obrišeš ovog studenta?"))
      return;

    setGreska(null);

    studentApi
      .delete(id)
      .then(() => ucitaj())
      .catch((error) => setGreska(error.message));
  };

  const sacuvaj = (podaci) => {
    const zahtev = zaIzmenu
      ? studentApi.update(zaIzmenu.id, podaci)
      : studentApi.create(podaci);
    zahtev
      .then(() => {
        setPrikaziFormu(false);
        setZaIzmenu(null);
        ucitaj();
      })
      .catch((error) => setGreska(error.message));
  };

  if (ucitavanje) return <div>Učitavanje...</div>;

  return (
    <div>
      {greska && <p className="text-danger">Greška: {greska}</p>}

      {prikaziFormu ? (
        <StudentForma
          postojeciStudent={zaIzmenu}
          onSacuvaj={sacuvaj}
          onOtkazi={() => {
            setGreska(null);
            setPrikaziFormu(false);
            setZaIzmenu(null);
          }}
        />
      ) : (
        <button
          className="btn btn-primary mb-3"
          onClick={() => {
            setGreska(null);
            setZaIzmenu(null);
            setPrikaziFormu(true);
          }}
        >
          Dodaj novog studenta
        </button>
      )}

      <table className="table table-striped">
        <thead>
          <tr>
            <th>ID</th>
            <th>Ime</th>
            <th>Prezime</th>
            <th>Broj indeksa</th>
            <th>Godina studija</th>
            <th>Akcije</th>
          </tr>
        </thead>
        <tbody>
          {lista.map((s) => (
            <tr key={s.id}>
              <td>{s.id}</td>
              <td>{s.ime}</td>
              <td>{s.prezime}</td>
              <td>{s.brojIndeksa}</td>
              <td>{s.godinaStudija}</td>
              <td>
                <button
                  className="btn btn-outline-primary btn-sm me-2"
                  onClick={() => {
                    setZaIzmenu(s);
                    setPrikaziFormu(true);
                  }}
                >
                  Izmeni
                </button>
                <button
                  className="btn btn-danger btn-sm"
                  onClick={() => obrisi(s.id)}
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

function PretragaTab() {
  const [pojamPretrage, setPojamPretrage] = useState("");
  const [rezultati, setRezultati] = useState([]);
  const [greska, setGreska] = useState(null);
  const [pretrazeno, setPretrazeno] = useState(false);

  const pretraziLica = (termin) => {
    liceApi
      .search(termin)
      .then((response) => {
        setRezultati(response.data);
        setPretrazeno(true);
      })
      .catch((error) => setGreska(error.message));
  };

   useEffect(() => {
    pretraziLica("");
  }, []);

  const posaljiPretragu = (e) => {
    e.preventDefault();
    pretraziLica(pojamPretrage);
  };

  const ponistiPretragu = () => {  
    setPojamPretrage("");
    setGreska(null);
    pretraziLica("");
  };

  return (
    <div>
      <form onSubmit={posaljiPretragu} className="d-flex gap-2 mb-3">
        <input
          className="form-control"
          style={{ maxWidth: "300px" }}
          placeholder="Ime i/ili prezime..."
          value={pojamPretrage}
          onChange={(e) => setPojamPretrage(e.target.value)}
        />
        <button type="submit" className="btn btn-outline-primary">
          Pretraži
        </button>

        <button
          type="button"
          className="btn btn-outline-secondary"
          onClick={ponistiPretragu}
          disabled={!pojamPretrage}
        >
          Poništi
        </button>
      </form>

      {greska && <p className="text-danger">Greška: {greska}</p>}

      {pretrazeno && rezultati.length === 0 && (
        <p className="text-muted">Nema rezultata za "{pojamPretrage}".</p>
      )}

      {rezultati.length > 0 && (
        <table className="table table-striped">
          <thead>
            <tr>
              <th>ID</th>
              <th>Ime</th>
              <th>Prezime</th>
              <th>Email</th>
              <th>Telefon</th>
            </tr>
          </thead>
          <tbody>
            {rezultati.map((l) => (
              <tr key={l.id}>
                <td>{l.id}</td>
                <td>{l.ime}</td>
                <td>{l.prezime}</td>
                <td>{l.email}</td>
                <td>{l.telefon}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default LicaPage;
