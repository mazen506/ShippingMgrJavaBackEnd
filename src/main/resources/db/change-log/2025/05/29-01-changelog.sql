-- liquibase formatted sql

-- changeset LENOVO:1748499285566-1
ALTER TABLE users
    ADD refresh_token1 VARCHAR(255) NULL;

-- changeset LENOVO:1748499285566-2
ALTER TABLE users DROP COLUMN refresh_token;

