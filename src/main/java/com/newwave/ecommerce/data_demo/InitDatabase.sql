create database ecommerce;
CREATE TABLE role (
                      role_id INT PRIMARY KEY,
                      role_name VARCHAR(50)
);

INSERT INTO role (role_id, role_name)
VALUES
    (0, 'USER'),
    (1, 'ADMIN'),
    (2, 'ROOT');