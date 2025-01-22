--liquibase formatted sql
--changeset igor_podg:3

CREATE TABLE IF NOT EXISTS message (
    id SERIAL PRIMARY KEY,
    consulate_employee_id INT NOT NULL,
    content TEXT NOT NULL,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
