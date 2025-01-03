-- noinspection SqlResolveForFile

drop table if exists formula_tag cascade;
drop table if exists formulas cascade;
drop table if exists roles cascade;
drop table if exists tags cascade;
drop table if exists users cascade;
drop table if exists variables cascade;

CREATE TABLE users
(
    user_id    BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    email      VARCHAR(250) NOT NULL UNIQUE,
    password   VARCHAR(68)  NOT NULL,
    first_name VARCHAR(100),
    last_name  VARCHAR(100)
);

CREATE TABLE roles
(
    role_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL,
    role    TEXT   NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE formulas
(
    formula_id    BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    user_id       BIGINT NOT NULL,
    latex_formula TEXT   NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE variables
(
    variable_id   BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    formula_id    BIGINT       NOT NULL,
    name          VARCHAR(255) NOT NULL,
    letter        CHAR(1)      NOT NULL,
    default_value FLOAT(53)    NULL_TO_DEFAULT,
    FOREIGN KEY (formula_id) REFERENCES formulas (formula_id) ON DELETE CASCADE
);

CREATE TABLE tags
(
    tag_id     BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name       VARCHAR(50) NOT NULL,
    formula_id BIGINT,
    FOREIGN KEY (formula_id) REFERENCES formulas (formula_id)
);

CREATE TABLE formula_tag
(
    formula_id BIGINT NOT NULL,
    tag_id     BIGINT NOT NULL,
    PRIMARY KEY (formula_id, tag_id),
    FOREIGN KEY (formula_id) REFERENCES formulas (formula_id),
    FOREIGN KEY (tag_id) REFERENCES tags (tag_id)
);