import { useState, useEffect } from "react";

const praznoStanje = {
  naziv: "",
  fakultetId: "",
};

function OrganizacionaJedinicaForma({
  postojecaJedinica,
  fakulteti,
  onSacuvaj,
  onOtkazi,
}) {
  const [podaci, setPodaci] = useState(praznoStanje);

  useEffect(() => {
    if (postojecaJedinica) {
      setPodaci({
        ...postojecaJedinica,
        fakultetId: postojecaJedinica.fakultetId ?? "",
      });
    } else {
      setPodaci(praznoStanje);
    }
  }, [postojecaJedinica]);

  const izmeniPolje = (e) => {
    const { name, value } = e.target;
    setPodaci((prev) => ({ ...prev, [name]: value }));
  };

  const posaljiFormu = (e) => {
    e.preventDefault();
    onSacuvaj({
      ...podaci,
      fakultetId: Number(podaci.fakultetId),
    });
  };

  return (
    <form onSubmit={posaljiFormu} className="mb-4 p-3 border rounded">
      <h4>
        {postojecaJedinica
          ? "Izmena organizacione jedinice"
          : "Nova organizaciona jedinica"}
      </h4>

      <div className="mb-2">
        <label className="form-label">Fakultet</label>
        <select
          className="form-select"
          name="fakultetId"
          value={podaci.fakultetId}
          onChange={izmeniPolje}
          required
        >
          <option value="">-- Izaberi fakultet --</option>
          {fakulteti.map((f) => (
            <option key={f.id} value={f.id}>
              {f.naziv}
            </option>
          ))}
        </select>
      </div>

      <div className="mb-3">
        <label className="form-label">Naziv</label>
        <input
          className="form-control"
          name="naziv"
          value={podaci.naziv}
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

export default OrganizacionaJedinicaForma;
