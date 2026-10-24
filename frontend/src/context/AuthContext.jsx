import { createContext, useContext, useState } from "react";

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [korisnik, setKorisnik] = useState(null);

  const prijaviKorisnika = (podaci) => setKorisnik(podaci);
  const odjaviKorisnika = () => setKorisnik(null);

  return (
    <AuthContext.Provider
      value={{ korisnik, prijaviKorisnika, odjaviKorisnika }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  return useContext(AuthContext);
}