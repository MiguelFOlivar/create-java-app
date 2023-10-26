CREATE DATABASE java_app;
USE java_app;
CREATE TABLE generos(id_genero INT NOT NULL AUTO_INCREMENT,
		genero VARCHAR(50) UNIQUE,
        PRIMARY KEY(id_genero));
        INSERT INTO generos(genero) VALUES
('Drama'),('Suspenso'), ('Terror'),('Acción'),('Comedia'),('Ciencia Ficción'),('Musical'),('Documental');
        
CREATE TABLE peliculas(id_pelicula INT NOT NULL AUTO_INCREMENT,
						titulo VARCHAR(100) NOT NULL UNIQUE,
                        url VARCHAR(255) NOT NULL,
                        imagen longblob,
                        genero_id INT NOT NULL,
                        PRIMARY KEY(id_pelicula),
                        FOREIGN KEY(genero_id) REFERENCES generos(id_genero));
                        