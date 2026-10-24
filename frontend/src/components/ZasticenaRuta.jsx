import { Navigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

function ZasticenaRuta({ children }) {
  const { korisnik } = useAuth();

  if (!korisnik) {
    return <Navigate to="/login" replace />;
  }

  return children;
}

export default ZasticenaRuta;