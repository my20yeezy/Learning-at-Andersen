CREATE DATABASE my_ticket_service
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LOCALE_PROVIDER = 'libc'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;


CREATE TABLE public."User"
(
    id character varying NOT NULL,
    name character varying,
    creation_date character varying,
    PRIMARY KEY (id)
);
ALTER TABLE IF EXISTS public."User"
    OWNER to postgres;


CREATE TABLE public."Ticket"
(
    id character varying NOT NULL,
    user_id character varying,
    ticket_type character varying,
    creation_date character varying,
    PRIMARY KEY (id)
);
ALTER TABLE IF EXISTS public."Ticket"
    OWNER to postgres;