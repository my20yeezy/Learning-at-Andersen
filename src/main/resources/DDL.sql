CREATE DATABASE my_ticket_service
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LOCALE_PROVIDER = 'libc'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;


CREATE TABLE user_info
(
    id VARCHAR(50) NOT NULL,
    name VARCHAR(50),
    creation_date VARCHAR(50),
    PRIMARY KEY (id)
);
ALTER TABLE IF EXISTS user_info
    OWNER to postgres;

CREATE TYPE ticket_type AS ENUM ('DAY', 'WEEK', 'MONTH', 'YEAR');

CREATE TABLE ticket_info
(
    id VARCHAR(50) NOT NULL,
    user_id VARCHAR(50),
    ticket_type ticket_type,
    creation_date VARCHAR(50),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user_info(id)
);
ALTER TABLE IF EXISTS ticket_info
    OWNER to postgres;