ALTER TABLE IF EXISTS public.employee
    ADD CONSTRAINT passport_id_unique UNIQUE (passport_id);

ALTER TABLE IF EXISTS public.employee
    drop column passport_id;

ALTER TABLE IF EXISTS public.employee
    add column passport_id text NOT NULL;