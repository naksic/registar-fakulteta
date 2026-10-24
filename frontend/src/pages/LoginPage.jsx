import { useState } from "react";
import { useNavigate } from "react-router-dom";
import korisnikApi from "../api/korisnikApi";
import { useAuth } from "../context/AuthContext";

function LoginPage() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [greska, setGreska] = useState(null);

  const { prijaviKorisnika } = useAuth();
  const navigate = useNavigate();

  const posaljiFormu = (e) => {
    e.preventDefault();
    setGreska(null);

    korisnikApi
      .login(username, password)
      .then((response) => {
        prijaviKorisnika(response.data);
        navigate("/univerziteti");
      })
      .catch((error) => {
        setGreska(
          error.response?.data?.poruka ||
            "Pogrešno korisničko ime ili lozinka"
        );
      });
  };

  return (
    <div className="container mt-5" style={{ maxWidth: "400px" }}>
      <h2 className="mb-4">Prijava</h2>

      {greska && <p className="text-danger">{greska}</p>}

      <form onSubmit={posaljiFormu}>
        <div className="mb-3">
          <label className="form-label">Korisničko ime</label>
          <input
            className="form-control"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Lozinka</label>
          <input
            className="form-control"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>

        <button type="submit" className="btn btn-primary w-100">
          Prijavi se
        </button>
      </form>
    </div>
  );
}

export default LoginPage;