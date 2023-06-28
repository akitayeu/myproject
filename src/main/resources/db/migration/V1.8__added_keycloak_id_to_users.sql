ALTER TABLE IF EXISTS public.users
    add column keycloak_id text NOT NULL default 'test';
