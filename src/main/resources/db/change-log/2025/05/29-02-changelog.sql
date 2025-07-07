-- liquibase formatted sql

-- changeset LENOVO:1748499384034-1

ALTER TABLE users
    ADD refresh_token VARCHAR(255) NULL;

-- changeset LENOVO:1748499384034-2
ALTER TABLE users DROP COLUMN refresh_token1;

