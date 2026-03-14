CREATE TABLE countries (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    continent VARCHAR(255) NOT NULL
);

CREATE TABLE hosts (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    country_id BIGINT REFERENCES countries(id)
);

CREATE TABLE accommodations (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
    name VARCHAR(255) NOT NULL,
    category VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    host_id BIGINT REFERENCES hosts(id),
    rooms INTEGER NOT NULL,
    rented BOOLEAN NOT NULL DEFAULT FALSE
);

-- Initial data for Country
INSERT INTO countries (name, continent)
VALUES ('Macedonia', 'Europe'),
       ('Germany', 'Europe');

-- Initial data for Host
INSERT INTO hosts (name, surname, country_id)
VALUES ('Petar', 'Petrovski', 1),
       ('Ana', 'Anova', 2);

-- Initial data for Accommodation
INSERT INTO accommodations (name, category, state, host_id, rooms, rented)
VALUES ('City Room', 'ROOM', 'GOOD', 1, 2, FALSE),
       ('Sea House', 'HOUSE', 'GOOD', 2, 4, FALSE);

