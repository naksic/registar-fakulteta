import { useState, useEffect } from "react";

const praznoStanje = {
  naziv: "",
  adresa: "",
  email: "",
  telefon: "",
  godinaOsnivanja: "",
  website: "",
};

function UniverzitetForma({ postojeciUniverzitet, onSacuvaj, onOtkazi }) {
  const [podaci, setPodaci] = useState(praznoStanje);

  useEffect(() => {
    if (postojeciUniverzitet) {
      setPodaci(postojeciUniverzitet);
    } else {
      setPodaci(praznoStanje);
    }
  }, [postojeciUniverzitet]);

  const izmeniPolje = (e) => {
    const { name, value } = e.target;
    setPodaci((prev) => ({ ...prev, [name]: value }));
  };

  const posaljiFormu = (e) => {
    e.preventDefault();
    onSacuvaj({
      ...podaci,
      godinaOsnivanja: Number(podaci.godinaOsnivanja),
    });
  };

  return (
    <form onSubmit={posaljiFormu} className="mb-4 p-3 border rounded">
      <h4>
        {postojeciUniverzitet ? "Izmena univerziteta" : "Novi univerzitet"}
      </h4>

      <div className="mb-2">
        <label className="form-label">Naziv</label>
        <input
          className="form-control"
          name="naziv"
          value={podaci.naziv}
          onChange={izmeniPolje}
          required
        />
      </div>

      <div className="mb-2">
        <label className="form-label">Adresa</label>
        <input
          className="form-control"
          name="adresa"
          value={podaci.adresa}
          onChange={izmeniPolje}
          required
        />
      </div>

      <div className="mb-2">
        <label className="form-label">Email</label>
        <input
          className="form-control"
          type="email"
          name="email"
          value={podaci.email}
          onChange={izmeniPolje}
          required
        />
      </div>

      <div className="mb-2">
        <label className="form-label">Telefon</label>
        <input
          className="form-control"
          name="telefon"
          value={podaci.telefon}
          onChange={izmeniPolje}
          required
        />
      </div>

      <div className="mb-2">
        <label className="form-label">Godina osnivanja</label>
        <input
          className="form-control"
          type="number"
          name="godinaOsnivanja"
          value={podaci.godinaOsnivanja}
          onChange={izmeniPolje}
          required
        />
      </div>

      <div className="mb-3">
        <label className="form-label">Website</label>
        <input
          className="form-control"
          name="website"
          value={podaci.website}
          onChange={izmeniPolje}
          required
        />
      </div>

      <button type="submit" className="btn btn-primary me-2">
        Sačuvaj
      </button>
      <button type="button" className="btn btn-secondary" onClick={onOtkazi}>
        Otkaži
      </button>
    </form>
  );
}

export default UniverzitetForma;
