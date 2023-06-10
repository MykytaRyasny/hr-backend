-- ------------- HR DB -----------

DROP DATABASE IF EXISTS RRHH;
CREATE DATABASE IF NOT EXISTS RRHH;
USE RRHH;

CREATE TABLE EMPLOYEES (
	FIRST_NAME VARCHAR(25) NOT NULL,
    LAST_NAME VARCHAR(25) NOT NULL,
    DNI VARCHAR(10) NOT NULL PRIMARY KEY,
    USERNAME VARCHAR(100) NOT NULL UNIQUE,
    PASSWORD VARCHAR(250) NOT NULL,
    ROLE VARCHAR(25) NOT NULL
);
-- Insert Employees
-- La contraseña es "admin" pero encriptada para poder hacer login en la base de datos.
insert into EMPLOYEES value ("admin", "admin", "admin", "admin@admin.com", "$2y$10$UV/a8YMeYzix/wSZFLRLsudUe4.qcPG1pFyaAzjSLy2RZZ4YIEoKG", "admin");
insert into EMPLOYEES value ("shop", "shop", "shop", "shop@shop.com", "$2y$10$UV/a8YMeYzix/wSZFLRLsudUe4.qcPG1pFyaAzjSLy2RZZ4YIEoKG", "shop_vendor");
insert into EMPLOYEES value ("shopm", "shopm", "shopm", "shopm@shopm.com", "$2y$10$UV/a8YMeYzix/wSZFLRLsudUe4.qcPG1pFyaAzjSLy2RZZ4YIEoKG", "shop_manager");
insert into EMPLOYEES value ("hr", "hr", "hr", "hr@hr.com", "$2y$10$UV/a8YMeYzix/wSZFLRLsudUe4.qcPG1pFyaAzjSLy2RZZ4YIEoKG", "hr");

-- ------------------ LIBRARY DB ------------------

DROP DATABASE IF EXISTS LIBRARY;
CREATE DATABASE IF NOT EXISTS LIBRARY;
USE LIBRARY;

CREATE TABLE BOOKS (
	ISBN VARCHAR(100) NOT NULL,
	TITLE VARCHAR(100) NOT NULL,
    RELEASE_DATE DATE NOT NULL,
    AUTOR VARCHAR(100) NOT NULL
);
-- Insert Library
INSERT INTO BOOKS (ISBN, TITLE, RELEASE_DATE, AUTOR) VALUES ('1234567890', 'La sombra del viento', '2001-05-01', 'Carlos Ruiz Zafón');
INSERT INTO BOOKS (ISBN, TITLE, RELEASE_DATE, AUTOR) VALUES ('9876543210', '1984', '1949-06-08', 'George Orwell');
INSERT INTO BOOKS (ISBN, TITLE, RELEASE_DATE, AUTOR) VALUES ('5678901234', 'El señor de los anillos', '1954-07-29', 'J.R.R. Tolkien');
INSERT INTO BOOKS (ISBN, TITLE, RELEASE_DATE, AUTOR) VALUES ('0987654321', 'Cien años de soledad', '1967-05-30', 'Gabriel García Márquez');
INSERT INTO BOOKS (ISBN, TITLE, RELEASE_DATE, AUTOR) VALUES ('5432109876', 'Don Quijote de la Mancha', '1605-01-16', 'Miguel de Cervantes');
