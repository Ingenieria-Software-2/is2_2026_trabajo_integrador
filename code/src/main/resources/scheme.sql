-- =============================================================
-- PERSONS — base table (superclass)
-- Contains all common attributes including authentication fields
-- =============================================================
CREATE TABLE IF NOT EXISTS persons (
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    dni         TEXT    NOT NULL UNIQUE,  -- TEXT: avoids leading zero issues
    name        TEXT       NOT NULL,
    surname     TEXT       NOT NULL,
    username    TEXT       NOT NULL UNIQUE,
    password    TEXT       NOT NULL,
    cellphone   TEXT,
    birthdate   DATE,
    email       TEXT       NOT NULL UNIQUE,

    created_at  DATETIME,
    updated_at  DATETIME
);

-- =============================================================
-- PERSON_ROLES — many roles per person
-- A person can be PROFESSOR and STUDENT simultaneously
-- =============================================================
CREATE TABLE IF NOT EXISTS person_roles (
    person_id   INTEGER NOT NULL,
    role        TEXT    NOT NULL CHECK(role IN ('ADMIN', 'PROFESSOR', 'STUDENT')),

    PRIMARY KEY (person_id, role),
    FOREIGN KEY (person_id) REFERENCES persons(id)
);

-- =============================================================
-- PROFESSORS — subclass of Person
-- Only stores attributes specific to Professor
-- =============================================================
CREATE TABLE IF NOT EXISTS professors (
    id              INTEGER PRIMARY KEY AUTOINCREMENT,
    person_id       INTEGER NOT NULL UNIQUE,
    degree          TEXT,
    graduate_univ   TEXT,
    position        TEXT,

    created_at      DATETIME,
    updated_at      DATETIME,

    FOREIGN KEY (person_id) REFERENCES persons(id)
);

-- =============================================================
-- STUDENTS — subclass of Person
-- Only stores attributes specific to Student
-- =============================================================
CREATE TABLE IF NOT EXISTS students (
    id                  INTEGER PRIMARY KEY AUTOINCREMENT,
    person_id           INTEGER NOT NULL UNIQUE,
    birthplace          TEXT,
    town_of_residence   TEXT,
    contact_relative    TEXT,
    contact_cellphone   TEXT,

    created_at          DATETIME,
    updated_at          DATETIME,

    FOREIGN KEY (person_id) REFERENCES persons(id)
);

-- =============================================================
-- ADMINISTRATORS — subclass of Person
-- No extra attributes, only the FK to persons
-- =============================================================
CREATE TABLE IF NOT EXISTS administrators (
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    person_id   INTEGER NOT NULL UNIQUE,

    created_at  DATETIME,
    updated_at  DATETIME,

    FOREIGN KEY (person_id) REFERENCES persons(id)
);