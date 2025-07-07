CREATE TABLE roles
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE user_roles
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_user_roles PRIMARY KEY (role_id, user_id)
);

ALTER TABLE users
    ADD access_locked BIT(1) NULL;

ALTER TABLE users
    ADD active BIT(1) NULL;

ALTER TABLE users
    ADD address VARCHAR(255) NULL;

ALTER TABLE users
    ADD confirmed BIT(1) NULL;

ALTER TABLE users
    ADD created_at datetime NULL;

ALTER TABLE users
    ADD created_by BIGINT NULL;

ALTER TABLE users
    ADD failed_attempt INT NULL;

ALTER TABLE users
    ADD locale VARCHAR(255) NULL;

ALTER TABLE users
    ADD lock_time datetime NULL;

ALTER TABLE users
    ADD phone_number VARCHAR(255) NULL;

ALTER TABLE users
    ADD photo VARCHAR(255) NULL;

ALTER TABLE users
    ADD updated_at datetime NULL;

ALTER TABLE users
    ADD updated_by BIGINT NULL;

ALTER TABLE users
    MODIFY access_locked BIT (1) NOT NULL;

ALTER TABLE users
    MODIFY active BIT (1) NOT NULL;

ALTER TABLE users
    MODIFY confirmed BIT (1) NOT NULL;

ALTER TABLE users
    MODIFY created_by BIGINT NOT NULL;

ALTER TABLE users
    MODIFY failed_attempt INT NOT NULL;

ALTER TABLE users
    MODIFY updated_by BIGINT NOT NULL;

ALTER TABLE roles
    ADD CONSTRAINT uc_roles_name UNIQUE (name);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT uc_users_name UNIQUE (name);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE users
DROP
COLUMN `role`;