ALTER TABLE IF EXISTS public.department
  ADD CONSTRAINT department_unique_name UNIQUE (department_name);

ALTER TABLE IF EXISTS public.role
    ADD CONSTRAINT role_unique_name UNIQUE (role_name);