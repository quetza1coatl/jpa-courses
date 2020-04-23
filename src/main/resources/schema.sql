DROP TABLE person
IF EXISTS;

DROP SEQUENCE hibernate_sequence
IF EXISTS;

CREATE SEQUENCE hibernate_sequence
  START WITH 1
  INCREMENT BY 1;

CREATE TABLE person(
    id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    birth_date TIMESTAMP
);