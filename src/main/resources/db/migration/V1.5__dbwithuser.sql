DROP TABLE IF EXISTS public.users CASCADE;

CREATE TABLE IF NOT EXISTS public.users
(
    employee_id integer NOT NULL references employee(employee_id),
    user_name text  NOT NULL,
    user_password text  NOT NULL,
    user_mail text NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (employee_id),
    CONSTRAINT user_unique_name UNIQUE (user_name),
    CONSTRAINT user_unique_mail UNIQUE (user_mail)
);






