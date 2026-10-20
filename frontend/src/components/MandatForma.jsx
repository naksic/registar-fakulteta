import { useState, useEffect } from 'react'

const praznoStanje = {
  fakultetId: '',
  datumPocetka: '',
  datumZavrsetka: '',
}

function MandatForma({ postojeciMandat, fakulteti, onSacuvaj, onOtkazi }) {
  const [podaci, setPodaci] = useState(praznoStanje)

  useEffect(() => {
    if (postojeciMandat) {
      setPodaci({
        ...postojeciMandat,
        fakultetId: postojeciMandat.fakultetId ?? '',
        datumZavrsetka: postojeciMandat.datumZavrsetka ?? '',
      })
    } else {
      setPodaci(praznoStanje)
    }
  }, [postojeciMandat])

  const izmeniPolje = (e) => {
    const { name, value } = e.target
    setPodaci(prev => ({ ...prev, [name]: value }))
  }

  const posaljiFormu = (e) => {
    e.preventDefault()
    onSacuvaj({
      ...podaci,
      fakultetId: Number(podaci.fakultetId),
      datumZavrsetka: podaci.datumZavrsetka || null,
    })
  }

  return (
    <form onSubmit={posaljiFormu} className="mb-4 p-3 border rounded">
      <h4>{postojeciMandat ? 'Izmena mandata' : 'Novi mandat'}</h4>

      <div className="mb-2">
        <label className="form-label">Fakultet</label>
        <select className="form-select" name="fakultetId" value={podaci.fakultetId} onChange={izmeniPolje} required>
          <option value="">-- Izaberi fakultet --</option>
          {fakulteti.map(f => (
            <option key={f.id} value={f.id}>{f.naziv}</option>
          ))}
        </select>
      </div>

      <div className="mb-2">
        <label className="form-label">Datum početka</label>
        <input className="form-control" type="date" name="datumPocetka" value={podaci.datumPocetka} onChange={izmeniPolje} required />
      </div>

      <div className="mb-3">
        <label className="form-label">Datum završetka (ostavi prazno ako je mandat aktivan)</label>
        <input className="form-control" type="date" name="datumZavrsetka" value={podaci.datumZavrsetka} onChange={izmeniPolje} />
      </div>

      <button type="submit" className="btn btn-primary me-2">Sačuvaj</button>
      <button type="button" className="btn btn-secondary" onClick={onOtkazi}>Otkaži</button>
    </form>
  )
}

export default MandatForma