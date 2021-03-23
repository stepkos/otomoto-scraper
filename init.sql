CREATE DATABASE otomoto;

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
    link varchar(300)
);