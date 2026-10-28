# Faculty Registry

**English** | [Srpski](README.sr.md)

A full-stack web application for managing a registry of universities and their faculties: organizational units, people (students, teaching and administrative staff) and faculty management boards with their mandates.

Seminar project for the Advanced Java Technologies course at the Faculty of Organizational Sciences (FON), University of Belgrade.

## Features

- CRUD for universities, faculties, organizational units, persons (students, teaching staff, administrative staff), board mandates and board members
- Faculty search by name, short name, university and status, with pagination, sorting by founding year and export to Excel
- Person search by first name, last name or both
- Business rules: board mandates of one faculty cannot overlap, every board function has one holder per mandate, a person holds one function per mandate, and a function must match the person type (e.g. a dean is a full professor)
- Login and logout with protected routes
- Centralized REST error handling with clear messages shown in the UI

## Tech stack

| Layer | Technologies |
|---|---|
| Backend | Java 17, Spring Boot 4.1, Spring Data JPA (Hibernate), Bean Validation, Lombok, Apache POI, Maven |
| Frontend | React 19, Vite, React Router, Axios, Bootstrap 5 |
| Database | MySQL |

## Project structure

```
├── backend/     Spring Boot REST API (controller, service, repository, mapper, dto, model, exception)
├── frontend/    React single-page application
└── database/    schema.sql (structure) and demo-data.sql (sample data)
```

## Getting started

### Prerequisites

JDK 17+, Node.js 18+, MySQL 8+

### 1. Database

Run the scripts in this order, using any MySQL client (e.g. SQLyog, MySQL Workbench) or the command line:

```bash
mysql -u root -p < database/schema.sql
mysql -u root -p 0_registar_fakulteta < database/demo-data.sql
```

The backend connects to `localhost:3306` with the user `admin` / `admin` by default. To use other credentials, set these environment variables before starting the backend:

| Variable | Default |
|---|---|
| `DB_URL` | `jdbc:mysql://localhost:3306/0_registar_fakulteta?useSSL=false&serverTimezone=UTC` |
| `DB_USERNAME` | `admin` |
| `DB_PASSWORD` | `admin` |

### 2. Backend

```bash
cd backend
./mvnw spring-boot:run        # Windows: mvnw.cmd spring-boot:run
```

The API is available at `http://localhost:8080/api`.

### 3. Frontend

```bash
cd frontend
npm install
npm run dev
```

Open `http://localhost:5173` and log in with the demo account from the `korisnik` table in `database/demo-data.sql`. The dev server proxies `/api` requests to the backend.

## API overview

| Base path | Resource |
|---|---|
| `/api/univerziteti` | Universities |
| `/api/fakulteti` | Faculties (search, paging, Excel export) |
| `/api/organizacione-jedinice` | Organizational units |
| `/api/lica` | Persons (search) |
| `/api/studenti` | Students |
| `/api/nastavno-osoblje` | Teaching staff |
| `/api/administrativno-osoblje` | Administrative staff |
| `/api/mandati-uprave` | Board mandates |
| `/api/clanovi-uprave` | Board members |
| `/api/korisnici` | Users (login, logout) |

## Note

Authentication is simplified for demonstration purposes: passwords are stored as plain text and only the UI routes are protected, not the API. Hardening it (password hashing and API-level authorization) is left as possible future work.
