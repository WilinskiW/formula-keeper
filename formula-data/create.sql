CREATE TABLE users
(
    user_id   SERIAL PRIMARY KEY,
    email     VARCHAR(250) NOT NULL UNIQUE,
    password  VARCHAR(250) NOT NULL,
    first_name VARCHAR(100),
    last_name  VARCHAR(100)
);

CREATE TABLE roles
(
    role_id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    role   TEXT   NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE formulas
(
    formula_id   SERIAL PRIMARY KEY,
    user_id      BIGINT NOT NULL,
    latex_formula TEXT   NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE variables
(
    variable_id   SERIAL PRIMARY KEY,
    formula_id    BIGINT       NOT NULL,
    name          VARCHAR(255) NOT NULL,
    letter        CHAR(1)    NOT NULL,
    default_value DECIMAL      NOT NULL,
    FOREIGN KEY (formula_id) REFERENCES formulas (formula_id) ON DELETE CASCADE
);

CREATE TABLE tags
(
    tag_id     SERIAL PRIMARY KEY,
    name       VARCHAR(50) NOT NULL,
    formula_id BIGINT,
    FOREIGN KEY (formula_id) REFERENCES formulas (formula_id)
);