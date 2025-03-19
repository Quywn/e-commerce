create database ecommerce;
CREATE TABLE Role (
                      role_id INT PRIMARY KEY,
                      role_name VARCHAR(50)
);

INSERT INTO Role (role_id, role_name)
VALUES
    (0, 'USER'),
    (1, 'ADMIN'),
    (2, 'MANAGER');