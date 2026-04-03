# Faktureringssystem – Backend (Spring Boot)

## Översikt

Backend-system för fakturering inom plåtslageriverksamhet.

Systemet hanterar:

* Jobb
* Material kopplat till jobb
* Kundtyper (PEAB/PRIVAT)
* Prisberäkning

---

## Domänmodell

### Jobb

* id (Long)
* beskrivning (String)
* kundTyp (Enum: PEAB, PRIVAT)
* skapadDatum (LocalDateTime)

Relation:

* Ett jobb har flera material

---

### Material

* id (Long)
* namn (String)
* antal (int)
* prisPerEnhet (BigDecimal)

Relation:

* Tillhör ett jobb

---

### KundTyp

* PEAB
* PRIVAT

---

### PrisConfig

* Hanterar prislogik beroende på kundtyp

---

## API Endpoints

### Jobb

* POST /jobb
* GET /jobb
* GET /jobb/{id}

---

## Funktion: Lägg till material på jobb

### Endpoint

POST /jobb/{id}/material

### Request Body

{
"namn": "Plåt",
"antal": 10,
"prisPerEnhet": 150
}

### Funktionalitet

* Hämta jobb via ID
* Skapa material
* Koppla material till jobb
* Spara i databasen

---

## ✅ Checklista (Krav från användaren)

### Grundfunktionalitet

* [ ] Skapa jobb
* [ ] Hämta alla jobb
* [ ] Hämta jobb via ID

### Materialhantering

* [ ] Endpoint finns: POST /jobb/{id}/material
* [ ] Material kan skickas in via JSON
* [ ] Material sparas i databasen
* [ ] Material kopplas korrekt till rätt jobb
* [ ] Flera material kan läggas till samma jobb

### Databas & relationer

* [ ] OneToMany mellan Jobb → Material
* [ ] ManyToOne mellan Material → Jobb
* [ ] Cascade fungerar korrekt (persist)

### Felhantering

* [ ] 404 om jobb inte finns
* [ ] Validering av input (antal > 0, pris > 0)

### Affärslogik

* [ ] Totalkostnad kan beräknas från material
* [ ] Kundtyp påverkar pris (via PrisConfig)

### Kodkvalitet

* [ ] Controller är tunn (logik i service)
* [ ] Service hanterar affärslogik
* [ ] Repository används korrekt (JPA)

### Testbarhet

* [ ] Endpoint testad via Postman / curl
* [ ] JSON request fungerar
* [ ] Data sparas korrekt i DB

---

## Tekniska krav

* Java 17+
* Spring Boot
* Spring Data JPA
* H2 / PostgreSQL

---

## Databasrelation

Jobb (1) → (N) Material

---

## Framtida utveckling

* Faktura (PDF)
* Moms
* Timpris
* Integration (Fortnox / Visma)
* Autentisering

---

## Definition of Done

✔ Kan skapa jobb
✔ Kan lägga till material på jobb
✔ Data sparas korrekt
✔ API fungerar utan fel
✔ Kod är strukturerad (controller/service/repository)

---
