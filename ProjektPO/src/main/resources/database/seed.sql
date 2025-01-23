--liquibase formatted sql
--changeset igor_podg:1

INSERT INTO countries (name, code) VALUES
    ('USA', 'USA'),
    ('Canada', 'CAN'),
    ('Germany', 'DEU'),
    ('France', 'FRA'),
    ('Italy', 'ITA'),
    ('United Kingdom', 'GBR'),
    ('Australia', 'AUS'),
    ('Japan', 'JPN'),
    ('China', 'CHN'),
    ('India', 'IND'),
    ('Mexico', 'MEX'),
    ('Norway', 'NOR'),
    ('Indonesia', 'IDN'),
    ('Brazil', 'BRA'),
    ('South Africa', 'ZAF'),
    ('Switzerland', 'CHE'),
    ('Iceland', 'ISL'),
    ('Argentina', 'ARG'),
    ('Sweden', 'SWE'),
    ('Saudi Arabia', 'SAU'),
    ('Philippines', 'PHL'),
    ('New Zealand', 'NZL');



INSERT INTO parameters (name, value) VALUES
                                         ('Warning download frequency', 5),
                                         ('Travel delete frequency', 30);