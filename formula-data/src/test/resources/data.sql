-- noinspection SqlResolveForFile

-- Insert test data for users
INSERT INTO users (email, password, first_name, last_name)
VALUES
    ('user1@example.com', 'password123', 'John', 'Doe'),
    ('user2@example.com', 'password456', 'Jane', 'Smith'),
    ('user3@example.com', 'password789', 'Alice', 'Johnson');

-- Insert test data for roles
INSERT INTO roles (user_id, role)
VALUES
    (1, 'ADMIN'),
    (1, 'USER'),
    (2, 'USER'),
    (3, 'MODERATOR');

-- Insert test data for formulas
INSERT INTO formulas (user_id, latex_formula)
VALUES
    (1, '\\frac{a}{b} + c'),
    (2, '\\sqrt{x^2 + y^2}'),
    (3, '\\int_{0}^{1} x dx');

-- Insert test data for variables
INSERT INTO variables (formula_id, name, letter, default_value)
VALUES
    (1, 'Numerator', 'a', 10.5),
    (1, 'Denominator', 'b', 5.2),
    (1, 'Constant', 'c', 3.0),
    (2, 'X-coordinate', 'x', 2.0),
    (2, 'Y-coordinate', 'y', 4.0),
    (3, 'Variable', 'x', 0.0);

-- Insert test data for tags
INSERT INTO tags (name, formula_id)
VALUES
    ('Arithmetic', 1),
    ('Geometry', 2),
    ('Integration', 3),
    ('Trigonometry', 2),
    ('Physics', 3);
