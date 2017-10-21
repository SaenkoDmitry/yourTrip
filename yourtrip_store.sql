DROP DATABASE IF EXISTS yourtripe;
CREATE DATABASE yourtrip;
USE yourtrip;

-- tables
-- Table: Person
CREATE TABLE IF NOT EXISTS Person (
    id serial,
    login varchar(20) NOT NULL,
    token varchar(200) NOT NULL,
    mail varchar(50) NOT NULL,
    birthday date NOT NULL,
    CONSTRAINT Person_pk PRIMARY KEY (id)
);

-- Table: Route
CREATE TABLE IF NOT EXISTS Route (
    id serial,
    nam varchar(100) NOT NULL,
    commentary varchar(200),
    complete bool NOT NULL,
    category ENUM('family', 'active', 'romantic', 'cognitive') NOT NULL,
    mark bit NOT NULL,
    person_id bigint unsigned NOT NULL,
    CONSTRAINT Route_pk PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (person_id) REFERENCES Person (id)
);

-- Table: Showplace
CREATE TABLE IF NOT EXISTS Showplace (
    id serial,
    nam varchar(50) NOT NULL,
    coords varchar(50) NOT NULL,
    mark bit NOT NULL,
    date date NOT NULL,
    route_id bigint unsigned NOT NULL,
    CONSTRAINT Showplace_pk PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (route_id) REFERENCES Route (id)
);

-- Table: Showplace_from_to
CREATE TABLE IF NOT EXISTS Showplace_from_to (
    id serial,
    person_id bigint unsigned NOT NULL,
    route_id bigint unsigned NOT NULL,
    spent_time time NOT NULL,
    distance int NOT NULL,
    CONSTRAINT Showplace_from_to_pk PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (person_id) REFERENCES Person (id),
    CONSTRAINT FOREIGN KEY (route_id) REFERENCES Route (id)
);

-- foreign keys
-- Reference: Route_Person (table: Route)
ALTER TABLE Route ADD CONSTRAINT FOREIGN KEY (person_id) REFERENCES Person (id);

-- Reference: Showplace_Route (table: Showplace)
ALTER TABLE Showplace ADD CONSTRAINT FOREIGN KEY (route_id) REFERENCES Route (id);

-- Reference: Showplace_from_to_Person (table: Showplace_from_to)
ALTER TABLE Showplace_from_to ADD CONSTRAINT FOREIGN KEY (person_id) REFERENCES Person (id);

-- Reference: Showplace_from_to_Route (table: Showplace_from_to)
ALTER TABLE Showplace_from_to ADD CONSTRAINT FOREIGN KEY (route_id) REFERENCES Route (id);
