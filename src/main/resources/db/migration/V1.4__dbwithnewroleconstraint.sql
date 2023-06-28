ALTER TABLE IF EXISTS public.role
    ADD CONSTRAINT role_unique_name UNIQUE (role_name);

