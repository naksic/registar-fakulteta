import { useState, useEffect } from "react";

const praznoStanje = {
  naziv: "",
  skraceniNaziv: "",
  adresa: "",
  email: "",
  telefon: "",
  godinaOsnivanja: "",
  pib: "",
  maticniBroj: "",
  website: "",
  status: "AKTIVAN",
  univerzitetId: "",
};

function FakultetForma({
  postojeciFakultet,
  univerziteti,
  onSacuvaj,
  onOtkazi,
}) {
  const [podaci, setPodaci] = useState(praznoStanje);

  useEffect(() => {
    if (postojeciFakultet) {
      setPodaci({
        ...postojeciFakultet,
        univerzitetId: postojeciFakultet.univerzitetId ?? "",
      });
    } else {
      setPodaci(praznoStanje);
    }
  }, [postojeciFakultet]);

  const izmeniPolje = (e) => {
    const { name, value } = e.target;
    setPodaci((prev) => ({ ...prev, [name]: value }));
  };

  const posaljiFormu = (e) => {
    e.preventDefault();
    onSacuvaj({
      ...podaci,
      godinaOsnivanja: Number(podaci.godinaOsnivanja),
      univerzitetId: Number(podaci.univerzitetId),
    });
  };

  return (
    <form onSubmit={posaljiFormu} className="mb-4 p-3 border rounded">
      <h4>{postojeciFakultet ? "Izmena fakulteta" : "Novi fakultet"}</h4>

      <div className="mb-2">
        <label className="form-label">Univerzitet</label>
        <select
          className="form-select"
          name="univerzitetId"
          value={podaci.univerzitetId}
          onChange={izmeniPolje}
          required
        >
          <option value="">-- Izaberi univerzitet --</option>
          {univerziteti.map((u) => (
            <option key={u.id} value={u.id}>
              {u.naziv}
            </option>
          ))}
        </select>
      </div>

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
        <label className="form-label">Skraćeni naziv</label>
        <input
          className="form-control"
          name="skraceniNaziv"
          value={podaci.skraceniNaziv}
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

      <div className="mb-2">
        <label className="form-label">PIB</label>
        <input
          className="form-control"
          name="pib"
          value={podaci.pib}
          onChange={izmeniPolje}
          required
        />
      </div>

      <div className="mb-2">
        <label className="form-label">Matični broj</label>
        <input
          className="form-control"
          name="maticniBroj"
          value={podaci.maticniBroj}
          onChange={izmeniPolje}
          required
        />
      </div>

      <div className="mb-2">
        <label className="form-label">Website</label>
        <input
          className="form-control"
          name="website"
          value={podaci.website}
          onChange={izmeniPolje}
          required
        />
      </div>

      <div className="mb-3">
        <label className="form-label">Status</label>
        <select
          className="form-select"
          name="status"
          value={podaci.status}
          onChange={izmeniPolje}
          required
        >
          <option value="AKTIVAN">AKTIVAN</option>
          <option value="NEAKTIVAN">NEAKTIVAN</option>
          <option value="UGASEN">UGAŠEN</option>
        </select>
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

export default FakultetForma;
