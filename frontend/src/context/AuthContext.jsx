import { createContext, useContext, useState } from "react";

const AuthContext = createContext(null);

function ucitajKorisnikaIzMemorije() {
  try {
    const sacuvano = localStorage.getItem("korisnik");
    return sacuvano ? JSON.parse(sacuvano) : null;
  } catch {
    return null;
  }
}

export function AuthProvider({ children }) {
  const [korisnik, setKorisnik] = useState(ucitajKorisnikaIzMemorije);

  const prijaviKorisnika = (podaci) => {
    localStorage.setItem("korisnik", JSON.stringify(podaci));
    setKorisnik(podaci);
  };

  const odjaviKorisnika = () => {
    localStorage.removeItem("korisnik");
    setKorisnik(null);
  };

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