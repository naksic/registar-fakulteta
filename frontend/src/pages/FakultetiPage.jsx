import { useEffect, useState } from 'react'
import fakultetApi from '../api/fakultetApi'
import univerzitetApi from '../api/univerzitetApi'
import FakultetForma from '../components/FakultetForma'

function FakultetiPage() {
    const [pojamPretrage, setPojamPretrage] = useState('')
  const [fakulteti, setFakulteti] = useState([])
  const [univerziteti, setUniverziteti] = useState([])
  const [greska, setGreska] = useState(null)
  const [ucitavanje, setUcitavanje] = useState(true)

  const [prikaziFormu, setPrikaziFormu] = useState(false)
  const [fakultetZaIzmenu, setFakultetZaIzmenu] = useState(null)


  const ucitajPodatke = () => {
    setUcitavanje(true)
    Promise.all([fakultetApi.getAll(), univerzitetApi.getAll()])
      .then(([fakultetiRes, univerzitetiRes]) => {
        setFakulteti(fakultetiRes.data)
        setUniverziteti(univerzitetiRes.data)
      })
      .catch(error => setGreska(error.message))
      .finally(() => setUcitavanje(false))
  }

  useEffect(() => {
    ucitajPodatke()
  }, [])

  const obrisiFakultet = (id) => {
    if (!window.confirm('Da li si siguran da želiš da obrišeš ovaj fakultet?')) return

    fakultetApi.delete(id)
      .then(() => ucitajPodatke())
      .catch(error => setGreska(error.message))
  }

  const otvoriFormuZaDodavanje = () => {
    setFakultetZaIzmenu(null)
    setPrikaziFormu(true)
  }

  const otvoriFormuZaIzmenu = (fakultet) => {
    setFakultetZaIzmenu(fakultet)
    setPrikaziFormu(true)
  }

  const zatvoriFormu = () => {
    setPrikaziFormu(false)
    setFakultetZaIzmenu(null)
  }

  const sacuvajFakultet = (podaci) => {
    const zahtev = fakultetZaIzmenu
      ? fakultetApi.update(fakultetZaIzmenu.id, podaci)
      : fakultetApi.create(podaci)

    zahtev
      .then(() => {
        zatvoriFormu()
        ucitajPodatke()
      })
      .catch(error => setGreska(error.message))
  }

  const pretraziFakultete = (e) => {
    e.preventDefault()
    setUcitavanje(true)
    fakultetApi.search(pojamPretrage)
        .then(response => setFakulteti(response.data))
        .catch(error => setGreska(error.message))
        .finally(() => setUcitavanje(false))
    }

const ocistiPretragu = () => {
    setPojamPretrage('')
    ucitajPodatke()
}

  if (ucitavanje) return <div className="container mt-4">Učitavanje...</div>

  return (
    <div className="container mt-4">
      <h1>Fakulteti</h1>

        <form onSubmit={pretraziFakultete} className="d-flex gap-2 mb-3">
            <input
                className="form-control"
                style={{ maxWidth: '300px' }}
                placeholder="Pretraga po nazivu..."
                value={pojamPretrage}
                onChange={e => setPojamPretrage(e.target.value)}
            />
            <button type="submit" className="btn btn-outline-primary">Pretraži</button>
            <button type="button" className="btn btn-outline-secondary" onClick={ocistiPretragu}>Poništi</button>
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
        <button className="btn btn-primary mb-3" onClick={otvoriFormuZaDodavanje}>
          Dodaj novi fakultet
        </button>
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
          {fakulteti.map(f => (
            <tr key={f.id}>
              <td>{f.id}</td>
              <td>{f.naziv}</td>
              <td>{f.skraceniNaziv}</td>
              <td>{f.nazivUniverziteta}</td>
              <td>{f.status}</td>
              <td>{f.website}</td>
              <td>
                <button className="btn btn-outline-primary btn-sm me-2" onClick={() => otvoriFormuZaIzmenu(f)}>
                  Izmeni
                </button>
                <button className="btn btn-danger btn-sm" onClick={() => obrisiFakultet(f.id)}>
                  Obriši
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

export default FakultetiPage