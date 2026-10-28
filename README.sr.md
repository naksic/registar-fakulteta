# Registar fakulteta

[English](README.md) | **Srpski**

Full-stack veb aplikacija za vođenje registra univerziteta i njihovih fakulteta: organizacionih jedinica, lica (studenti, nastavno i administrativno osoblje) i uprava fakulteta sa njihovim mandatima.

Seminarski rad iz predmeta Napredne Java tehnologije na Fakultetu organizacionih nauka (FON), Univerzitet u Beogradu.

## Funkcionalnosti

- CRUD za univerzitete, fakultete, organizacione jedinice, lica (studenti, nastavno osoblje, administrativno osoblje), mandate uprave i članove uprave
- Pretraga fakulteta po nazivu, skraćenom nazivu, univerzitetu i statusu, sa straničenjem, sortiranjem po godini osnivanja i izvozom u Excel
- Pretraga lica po imenu, prezimenu ili oba
- Poslovna pravila: mandati uprave jednog fakulteta ne smeju da se preklapaju, svaka funkcija u upravi ima jednog nosioca po mandatu, jedno lice obavlja jednu funkciju po mandatu, a funkcija mora odgovarati tipu lica (npr. dekan je redovni profesor)
- Prijava i odjava, uz zaštićene rute
- Centralizovana obrada grešaka u REST API-ju, sa jasnim porukama u korisničkom interfejsu

## Tehnologije

| Sloj | Tehnologije |
|---|---|
| Backend | Java 17, Spring Boot 4.1, Spring Data JPA (Hibernate), Bean Validation, Lombok, Apache POI, Maven |
| Frontend | React 19, Vite, React Router, Axios, Bootstrap 5 |
| Baza podataka | MySQL |

## Struktura projekta

```
├── backend/     Spring Boot REST API (controller, service, repository, mapper, dto, model, exception)
├── frontend/    React single-page aplikacija
└── database/    schema.sql (struktura) i demo-data.sql (probni podaci)
```

## Pokretanje

### Preduslovi

JDK 17+, Node.js 18+, MySQL 8+

### 1. Baza podataka

Skripte pokreni ovim redom, u bilo kom MySQL klijentu (npr. SQLyog, MySQL Workbench) ili iz komandne linije:

```bash
mysql -u root -p < database/schema.sql
mysql -u root -p 0_registar_fakulteta < database/demo-data.sql
```

Backend se podrazumevano povezuje na `localhost:3306` sa korisnikom `admin` / `admin`. Za drugačije podatke postavi ove promenljive okruženja pre pokretanja backenda:

| Promenljiva | Podrazumevana vrednost |
|---|---|
| `DB_URL` | `jdbc:mysql://localhost:3306/0_registar_fakulteta?useSSL=false&serverTimezone=UTC` |
| `DB_USERNAME` | `admin` |
| `DB_PASSWORD` | `admin` |

### 2. Backend

```bash
cd backend
./mvnw spring-boot:run        # Windows: mvnw.cmd spring-boot:run
```

API je dostupan na `http://localhost:8080/api`.

### 3. Frontend

```bash
cd frontend
npm install
npm run dev
```

Otvori `http://localhost:5173` i prijavi se demo nalogom iz tabele `korisnik` u fajlu `database/demo-data.sql`. Razvojni server prosleđuje `/api` zahteve na backend.

## Pregled API-ja

| Putanja | Resurs |
|---|---|
| `/api/univerziteti` | Univerziteti |
| `/api/fakulteti` | Fakulteti (pretraga, straničenje, izvoz u Excel) |
| `/api/organizacione-jedinice` | Organizacione jedinice |
| `/api/lica` | Lica (pretraga) |
| `/api/studenti` | Studenti |
| `/api/nastavno-osoblje` | Nastavno osoblje |
| `/api/administrativno-osoblje` | Administrativno osoblje |
| `/api/mandati-uprave` | Mandati uprave |
| `/api/clanovi-uprave` | Članovi uprave |
| `/api/korisnici` | Korisnici (prijava, odjava) |

## Napomena

Autentifikacija je pojednostavljena i služi samo za demonstraciju: lozinke se čuvaju kao običan tekst, a zaštićene su samo rute u korisničkom interfejsu, ne i API. Njeno unapređenje (heširanje lozinki i zaštita API-ja) ostavljeno je kao mogući budući rad.
