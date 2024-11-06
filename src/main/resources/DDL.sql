CREATE DATABASE my_ticket_service
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LOCALE_PROVIDER = 'libc'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE TABLE user_info (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255),
    creation_date_time TIMESTAMP
);

CREATE TYPE ticket_type AS ENUM ('DAY', 'WEEK', 'MONTH', 'YEAR');

CREATE TABLE ticket_info (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    time TIMESTAMP,
    is_promo BOOLEAN,
    price DOUBLE PRECISION,
    creation_date_time TIMESTAMP,
    ticket_type VARCHAR(255),
    user_id UUID,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user_info (id) ON DELETE SET NULL
);