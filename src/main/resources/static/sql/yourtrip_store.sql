DROP DATABASE IF EXISTS yourtrip;
CREATE DATABASE yourtrip;
SET NAMES 'utf8';
SET CHARACTER SET 'utf8';
USE yourtrip;

-- tables
-- Table: Person
CREATE TABLE IF NOT EXISTS person (
    id serial,
    login varchar(100) NOT NULL,
    nickname varchar(100) NOT NULL,
    avatar varchar(200) NOT NULL,
    hash varchar(100) NOT NULL,
    mail varchar(255) NOT NULL,
    birthday date NOT NULL,
    role enum('normal', 'admin') NOT NULL,
    hidden_nickname bool,
    hidden_mail bool,
    hidden_birthday bool,
    PRIMARY KEY (id),
    UNIQUE (login)
) DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE IF NOT EXISTS subscription (
    id serial,
    person_id_from bigint unsigned NOT NULL,
    person_id_to bigint unsigned NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (Person_id_from, Person_id_to)
) DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Table: Route
CREATE TABLE IF NOT EXISTS route (
    id serial,
    route_name varchar(100) NOT NULL,
    commentary varchar(1000),
    complete bool NOT NULL,
    lower_price int not null,
    upper_price int not null,
    hidden bool NOT NULL,
    category enum('family', 'active', 'romantic', 'cognitive') NOT NULL,
    mark double NOT NULL,
    person_id bigint unsigned NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (route_name)
) DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Table: Showplace
CREATE TABLE IF NOT EXISTS showplace (
    id serial,
    showplace_name varchar(100) NOT NULL,
    coords varchar(50) NOT NULL,
    avg_mark double NOT NULL,
    num_of_marks int NOT NULL,
    visit_date date NOT NULL,
    CONSTRAINT Showplace_pk PRIMARY KEY (id),
    CONSTRAINT Showplace_un UNIQUE (showplace_name)
) DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Table: Showplace_from_to
CREATE TABLE IF NOT EXISTS showplace_from_to (
    id serial,
    person_id bigint unsigned NOT NULL,
    route_id bigint unsigned NOT NULL,
    spent_time time NOT NULL,
    distance int NOT NULL,
    showplace_from_id bigint unsigned NOT NULL,
    showplace_to_id bigint unsigned NOT NULL,
    CONSTRAINT Showplace_from_to_pk PRIMARY KEY (id)
) DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Table: Route_showplace_list
CREATE TABLE IF NOT EXISTS route_showplace_list (
    person_id bigint unsigned NOT NULL,
    route_id bigint unsigned NOT NULL,
    showplace_id bigint unsigned NOT NULL,
    index_number int NOT NULL,
    visit_date date NOT NULL,
    showplace_mark double NOT NULL,
    CONSTRAINT Route_showplace_list_pk PRIMARY KEY (person_id, route_id, showplace_id),
    CONSTRAINT Route_showplace_list_un UNIQUE (person_id, route_id, index_number)
) DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Table: Image
CREATE TABLE IF NOT EXISTS image (
    id serial,
    route_id bigint unsigned NOT NULL,
    url varchar(200) NOT NULL,
    CONSTRAINT Image_pk PRIMARY KEY (id)
) DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- foreign keys

-- Reference: Route (table: Person)
ALTER TABLE route ADD FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE CASCADE;

-- Reference: Showplace_from_to (table: Person)
ALTER TABLE showplace_from_to ADD FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE CASCADE;

-- Reference: Showplace_from_to (table: Route)
ALTER TABLE showplace_from_to ADD FOREIGN KEY (route_id) REFERENCES route (id) ON DELETE CASCADE;

-- Reference: Showplace_from_to (table: Showplace)
ALTER TABLE showplace_from_to ADD FOREIGN KEY (showplace_from_id) REFERENCES showplace (id) ON DELETE CASCADE;

-- Reference: Showplace_from_to (table: Showplace)
ALTER TABLE showplace_from_to ADD FOREIGN KEY (showplace_to_id) REFERENCES showplace (id) ON DELETE CASCADE;

-- Reference: Route_showplace_list_Person (table: Route_showplace_list)
ALTER TABLE route_showplace_list ADD FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE CASCADE;

-- Reference: Route_showplace_list_Route (table: Route_showplace_list)
ALTER TABLE route_showplace_list ADD FOREIGN KEY (route_id) REFERENCES route (id) ON DELETE CASCADE;

-- Reference: Route_showplace_list_Showplace (table: Route_showplace_list)
ALTER TABLE route_showplace_list ADD FOREIGN KEY (showplace_id) REFERENCES showplace (id) ON DELETE CASCADE;

-- Reference: Subscription (table: Person)
ALTER TABLE subscription ADD FOREIGN KEY (person_id_from) REFERENCES person (id) ON DELETE CASCADE;

-- Reference: Subscription (table: Person)
ALTER TABLE subscription ADD FOREIGN KEY (person_id_to) REFERENCES person (id) ON DELETE CASCADE;

-- Reference: Route (table: Route)
ALTER TABLE image ADD FOREIGN KEY (route_id) REFERENCES route (id) ON DELETE CASCADE;