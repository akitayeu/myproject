ALTER Table if exists public.users
    drop column keycloak_id;
ALTER TABLE IF EXISTS public.users
    add column keycloak_id text default NULL;
