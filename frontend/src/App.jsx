import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { AuthProvider } from "./context/AuthContext";
import Navigacija from "./components/Navigacija";
import ZasticenaRuta from "./components/ZasticenaRuta";
import LoginPage from "./pages/LoginPage";
import UniverzitetiPage from "./pages/UniverzitetiPage";
import FakultetiPage from "./pages/FakultetiPage";
import OrganizacioneJedinicePage from "./pages/OrganizacioneJedinicePage";
import LicaPage from "./pages/LicaPage";
import MandatiPage from "./pages/MandatiPage";

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Navigacija />
        <Routes>
          <Route path="/login" element={<LoginPage />} />

          <Route path="/" element={<Navigate to="/univerziteti" replace />} />

          <Route
            path="/univerziteti"
            element={
              <ZasticenaRuta>
                <UniverzitetiPage />
              </ZasticenaRuta>
            }
          />
          <Route
            path="/fakulteti"
            element={
              <ZasticenaRuta>
                <FakultetiPage />
              </ZasticenaRuta>
            }
          />
          <Route
            path="/organizacione-jedinice"
            element={
              <ZasticenaRuta>
                <OrganizacioneJedinicePage />
              </ZasticenaRuta>
            }
          />
          <Route
            path="/lica"
            element={
              <ZasticenaRuta>
                <LicaPage />
              </ZasticenaRuta>
            }
          />
          <Route
            path="/mandati"
            element={
              <ZasticenaRuta>
                <MandatiPage />
              </ZasticenaRuta>
            }
          />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;