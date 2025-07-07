ALTER TABLE users
    ADD branch_id INT NULL;

ALTER TABLE users
    ADD refresh_token VARCHAR(255) NULL;

ALTER TABLE users
    ADD refresh_token_expire_time datetime NULL;

ALTER TABLE users
DROP
COLUMN created_at;

ALTER TABLE users
DROP
COLUMN created_by;

ALTER TABLE users
DROP
COLUMN updated_at;

ALTER TABLE users
DROP
COLUMN updated_by;