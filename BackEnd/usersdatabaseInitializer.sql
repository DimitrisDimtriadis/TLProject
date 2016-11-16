CREATE DATABASE usersdatabase
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Greek_Greece.1253'
    LC_CTYPE = 'Greek_Greece.1253'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;







CREATE TABLE public.users
(
    id integer NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    email character(25) COLLATE pg_catalog."default" NOT NULL,
    username character(10) COLLATE pg_catalog."default" NOT NULL,
    password character(10) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;