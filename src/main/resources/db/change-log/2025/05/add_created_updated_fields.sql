-- liquibase formatted sql

-- changeset LENOVO:1748499384034-1

ALTER TABLE users
        add column created_at TIMESTAMP;

alter table users
        add column created_by BIGINT;

alter table users
        add column  updated_at TIMESTAMP;

alter table users
        add column  updated_by BIGINT;


