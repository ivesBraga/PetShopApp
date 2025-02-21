DROP DATABASE petshop;

CREATE DATABASE petshop;

USE petshop;

CREATE TABLE pets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nomeDono VARCHAR(100),
    nomeAnimal VARCHAR(100),
    especie VARCHAR(50),
    endereco VARCHAR(200),
    alergia VARCHAR(200),
    peso DOUBLE,
    idade INT,
    observacao TEXT
);


SELECT * FROM pets;




