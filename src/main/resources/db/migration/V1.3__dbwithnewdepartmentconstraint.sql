ALTER TABLE IF EXISTS public.department
  ADD CONSTRAINT department_unique_name UNIQUE (department_name);

