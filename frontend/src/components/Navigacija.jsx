import { NavLink } from 'react-router-dom'

function Navigacija() {
  const stilLinka = ({ isActive }) =>
    isActive ? 'nav-link active fw-bold' : 'nav-link'

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
      <div className="container">
        <span className="navbar-brand">Registar fakulteta</span>
        <div className="navbar-nav">
          <NavLink to="/univerziteti" className={stilLinka}>Univerziteti</NavLink>
          <NavLink to="/fakulteti" className={stilLinka}>Fakulteti</NavLink>
          <NavLink to="/organizacione-jedinice" className={stilLinka}>Organizacione jedinice</NavLink>
          <NavLink to="/lica" className={stilLinka}>Lica</NavLink>
          <NavLink to="/mandati" className={stilLinka}>Mandati uprave</NavLink>
        </div>
      </div>
    </nav>
  )
}

export default Navigacija