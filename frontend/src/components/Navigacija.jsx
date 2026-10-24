import { NavLink, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import korisnikApi from "../api/korisnikApi";

function Navigacija() {
  const { korisnik, odjaviKorisnika } = useAuth();
  const navigate = useNavigate();

  const stilLinka = ({ isActive }) =>
    isActive ? "nav-link active fw-bold" : "nav-link";

  const odjaviSe = () => {
    korisnikApi
      .logout(korisnik.id)
      .finally(() => {
        odjaviKorisnika();
        navigate("/login");
      });
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
      <div className="container">
        <span className="navbar-brand">Registar fakulteta</span>

        {korisnik && (
          <>
            <div className="navbar-nav">
              <NavLink to="/univerziteti" className={stilLinka}>
                Univerziteti
              </NavLink>
              <NavLink to="/fakulteti" className={stilLinka}>
                Fakulteti
              </NavLink>
              <NavLink to="/organizacione-jedinice" className={stilLinka}>
                Organizacione jedinice
              </NavLink>
              <NavLink to="/lica" className={stilLinka}>
                Lica
              </NavLink>
              <NavLink to="/mandati" className={stilLinka}>
                Mandati uprave
              </NavLink>
            </div>

            <div className="d-flex align-items-center ms-auto">
              <span className="text-light me-3">
                {korisnik.username}
              </span>
              <button
                className="btn btn-outline-light btn-sm"
                onClick={odjaviSe}
              >
                Odjavi se
              </button>
            </div>
          </>
        )}
      </div>
    </nav>
  );
}

export default Navigacija;