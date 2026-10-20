import { useState, useEffect } from 'react'

const praznoStanje = {
  ime: '',
  prezime: '',
  datumRodjenja: '',
  email: '',
  telefon: '',
  radnoMesto: '',
  sektor: '',
}

function AdministrativnoOsobljeForma({ postojeciZaposleni, onSacuvaj, onOtkazi }) {
  const [podaci, setPodaci] = useState(praznoStanje)

  useEffect(() => {
    setPodaci(postojeciZaposleni ?? praznoStanje)
  }, [postojeciZaposleni])

  const izmeniPolje = (e) => {
    const { name, value } = e.target
    setPodaci(prev => ({ ...prev, [name]: value }))
  }

  const posaljiFormu = (e) => {
    e.preventDefault()
    onSacuvaj(podaci)
  }

  return (
    <form onSubmit={posaljiFormu} className="mb-4 p-3 border rounded">
      <h4>{postojeciZaposleni ? 'Izmena administrativnog osoblja' : 'Novo administrativno osoblje'}</h4>

      <div className="mb-2">
        <label className="form-label">Ime</label>
        <input className="form-control" name="ime" value={podaci.ime} onChange={izmeniPolje} required />
      </div>

      <div className="mb-2">
        <label className="form-label">Prezime</label>
        <input className="form-control" name="prezime" value={podaci.prezime} onChange={izmeniPolje} required />
      </div>

      <div className="mb-2">
        <label className="form-label">Datum rođenja</label>
        <input className="form-control" type="date" name="datumRodjenja" value={podaci.datumRodjenja} onChange={izmeniPolje} required />
      </div>

      <div className="mb-2">
        <label className="form-label">Email</label>
        <input className="form-control" type="email" name="email" value={podaci.email} onChange={izmeniPolje} required />
      </div>

      <div className="mb-2">
        <label className="form-label">Telefon</label>
        <input className="form-control" name="telefon" value={podaci.telefon} onChange={izmeniPolje} required />
      </div>

      <div className="mb-2">
        <label className="form-label">Radno mesto</label>
        <input className="form-control" name="radnoMesto" value={podaci.radnoMesto} onChange={izmeniPolje} required />
      </div>

      <div className="mb-3">
        <label className="form-label">Sektor</label>
        <input className="form-control" name="sektor" value={podaci.sektor} onChange={izmeniPolje} required />
      </div>

      <button type="submit" className="btn btn-primary me-2">Sačuvaj</button>
      <button type="button" className="btn btn-secondary" onClick={onOtkazi}>Otkaži</button>
    </form>
  )
}

export default AdministrativnoOsobljeForma