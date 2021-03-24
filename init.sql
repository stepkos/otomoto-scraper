CREATE DATABASE otomoto;

USE otomoto;

CREATE TABLE cars (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    title varchar(100),
    subtitle varchar(100),
    year varchar(100),
    mileage varchar(100),
    engine_capacity varchar(100),
    price varchar(100),
    fuel_type varchar(100),
    price_currency varchar(100),
    location varchar(100),
    link varchar(300),
    time DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    username varchar(30) NOT NULL UNIQUE,
    password varchar(30) NOT NULL
);

INSERT INTO users VALUES
(NULL, "Janek", "janek123"),
(NULL, "Kamil", "kamil321"),
(NULL, "Jakub", "jakub100");