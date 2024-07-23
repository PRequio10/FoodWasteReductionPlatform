DROP DATABASE IF EXISTS FWRP;
CREATE DATABASE FWRP;
USE FWRP;

CREATE TABLE Users (
    user_id INT PRIMARY KEY auto_increment,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone INT NOT NULL,
    user_type VARCHAR(50) 
    CHECK (user_type IN ('Retailer', 'Consumer', 'Charitable Organization')) NOT NULL
);

CREATE TABLE SurplusFood (
	foodID INT PRIMARY KEY auto_increment,
    foodName VARCHAR(100) NOT NULL,
    quatity INT NOT NULL,
    price INT NOT NULL,
    expDate date NOT NULL,
    remarks VARCHAR(100) NULL
);
