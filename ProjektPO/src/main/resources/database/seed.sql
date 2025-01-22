--liquibase formatted sql
--changeset igor_podg:1

INSERT INTO countries (name) VALUES
                                 ('USA'),
                                 ('Canada'),
                                 ('Germany'),
                                 ('France'),
                                 ('Italy'),
                                 ('United Kingdom'),
                                 ('Australia'),
                                 ('Japan'),
                                 ('China'),
                                 ('India');


INSERT INTO parameters (name, value) VALUES
                                         ('Warning download frequency', 5),
                                         ('Travel delete frequency', 30);