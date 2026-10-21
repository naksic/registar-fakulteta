import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Navigacija from "./components/Navigacija";
import UniverzitetiPage from "./pages/UniverzitetiPage";
import FakultetiPage from "./pages/FakultetiPage";
import OrganizacioneJedinicePage from "./pages/OrganizacioneJedinicePage";
import LicaPage from "./pages/LicaPage";
import MandatiPage from "./pages/MandatiPage";

function App() {
  return (
    <BrowserRouter>
      <Navigacija />
      <Routes>
        <Route path="/" element={<Navigate to="/univerziteti" replace />} />
        <Route path="/univerziteti" element={<UniverzitetiPage />} />
        <Route path="/fakulteti" element={<FakultetiPage />} />
        <Route
          path="/organizacione-jedinice"
          element={<OrganizacioneJedinicePage />}
        />
        <Route path="/lica" element={<LicaPage />} />
        <Route path="/mandati" element={<MandatiPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
