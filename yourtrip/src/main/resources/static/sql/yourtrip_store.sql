DROP DATABASE IF EXISTS yourtrip;
CREATE DATABASE yourtrip;
USE yourtrip;

-- tables
-- Table: Person
CREATE TABLE IF NOT EXISTS Person (
    id serial,
    login varchar(50) NOT NULL,
    hash varchar(200) NOT NULL,
    mail varchar(50) NOT NULL,
    birthday date NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (login)
);

-- Table: Route
CREATE TABLE IF NOT EXISTS Route (
    id serial,
    route_name varchar(100) NOT NULL,
    commentary varchar(200),
    complete bool NOT NULL,
    category enum('family', 'active', 'romantic', 'cognitive') NOT NULL,
    mark int NOT NULL,
    person_id bigint unsigned NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (route_name)
);

-- Table: Showplace
CREATE TABLE IF NOT EXISTS Showplace (
    id serial,
    showplace_name varchar(100) NOT NULL,
    coords varchar(50) NOT NULL,
    avg_mark int NOT NULL,
    num_of_marks int NOT NULL,
    visit_date date NOT NULL,
    CONSTRAINT Showplace_pk PRIMARY KEY (id),
    CONSTRAINT Showplace_un UNIQUE (showplace_name)
);

-- Table: Showplace_from_to
CREATE TABLE IF NOT EXISTS Showplace_from_to (
    id serial,
    person_id bigint unsigned NOT NULL,
    route_id bigint unsigned NOT NULL,
    spent_time time NOT NULL,
    distance int NOT NULL,
    CONSTRAINT Showplace_from_to_pk PRIMARY KEY (id)
);

-- Table: Route_showplace_list
CREATE TABLE IF NOT EXISTS Route_showplace_list (
    person_id bigint unsigned NOT NULL,
    route_id bigint unsigned NOT NULL,
    showplace_id bigint unsigned NOT NULL,
    index_number int NOT NULL,
    CONSTRAINT Route_showplace_list_pk PRIMARY KEY (person_id, route_id, showplace_id),
    CONSTRAINT Route_showplace_list_un UNIQUE (person_id, route_id, index_number)
);

-- foreign keys

-- Reference: Route (table: Person)
ALTER TABLE Route ADD FOREIGN KEY (person_id) REFERENCES Person (id) ON DELETE CASCADE;

-- Reference: Showplace_from_to_Person (table: Showplace_from_to)
ALTER TABLE Showplace_from_to ADD FOREIGN KEY (person_id) REFERENCES Person (id) ON DELETE CASCADE;

-- Reference: Showplace_from_to_Route (table: Showplace_from_to)
ALTER TABLE Showplace_from_to ADD FOREIGN KEY (route_id) REFERENCES Route (id) ON DELETE CASCADE;

-- Reference: Route_showplace_list_Person (table: Route_showplace_list)
ALTER TABLE Route_showplace_list ADD FOREIGN KEY (person_id) REFERENCES Person (id) ON DELETE CASCADE;

-- Reference: Route_showplace_list_Route (table: Route_showplace_list)
ALTER TABLE Route_showplace_list ADD FOREIGN KEY (route_id) REFERENCES Route (id) ON DELETE CASCADE;

-- Reference: Route_showplace_list_Showplace (table: Route_showplace_list)
ALTER TABLE Route_showplace_list ADD FOREIGN KEY (showplace_id) REFERENCES Showplace (id) ON DELETE CASCADE;