
DROP TABLE IF EXISTS public.department CASCADE;

CREATE SEQUENCE department_id_seq;

CREATE TABLE IF NOT EXISTS public.department
(
    department_id integer DEFAULT nextval('department_id_seq') NOT NULL,
    department_name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT department_pkey PRIMARY KEY (department_id)
);

DROP TABLE IF EXISTS public.employee CASCADE;

CREATE SEQUENCE employee_id_seq;

CREATE TABLE IF NOT EXISTS public.employee
(
    employee_id integer DEFAULT nextval('employee_id_seq')  NOT NULL,
    firstname text COLLATE pg_catalog."default" NOT NULL,
    lastname text COLLATE pg_catalog."default" NOT NULL,
    birthdate date NOT NULL,
    gender "char" NOT NULL,
    passport_id character(1) COLLATE pg_catalog."default" NOT NULL,
    passport_validity date NOT NULL,
    department_id integer NOT NULL,
    CONSTRAINT employee_pkey PRIMARY KEY (employee_id)
);

DROP TABLE IF EXISTS public.employee_role;

CREATE SEQUENCE employee_role_id_seq;

CREATE TABLE IF NOT EXISTS public.employee_role
(
    counter integer DEFAULT nextval('employee_role_id_seq') NOT NULL,
    employee_id integer NOT NULL,
    role_id integer NOT NULL,
    CONSTRAINT employee_role_pkey PRIMARY KEY (counter)
);

DROP TABLE IF EXISTS public.role CASCADE;

CREATE SEQUENCE role_id_seq;

CREATE TABLE IF NOT EXISTS public.role
(
    role_id integer DEFAULT nextval('role_id_seq') NOT NULL,
    role_name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (role_id)
);


ALTER TABLE IF EXISTS public.employee
    ADD CONSTRAINT employee FOREIGN KEY (department_id)
        REFERENCES public.department (department_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.employee_role
    ADD CONSTRAINT employee_id FOREIGN KEY (employee_id)
        REFERENCES public.employee (employee_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.employee_role
    ADD CONSTRAINT role_id FOREIGN KEY (role_id)
        REFERENCES public.role (role_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;

