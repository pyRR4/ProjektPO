--liquibase formatted sql
--changeset igor_podg:3

CREATE TABLE IF NOT EXISTS countries (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS consulates (
    id SERIAL PRIMARY KEY,
    code VARCHAR(255) NOT NULL UNIQUE,
    country INTEGER NOT NULL REFERENCES countries(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS parameters (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    value INTEGER NOT NULL,
    updated_at DATE NOT NULL DEFAULT NOW(),
    created_at DATE NOT NULL DEFAULT NOW()
);