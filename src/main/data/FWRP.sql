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

CREATE TABLE Inventory (
    item_id INT  PRIMARY KEY auto_increment,
    retailer_id INT,
    item_name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    expiration_date DATE NOT NULL,
    status VARCHAR(50) DEFAULT 'Available' CHECK (status IN ('Available', 'Surplus', 'Claimed', 'Purchased')),
    REFERENCES Users(user_id)
);