drop database if exists jaiskibel;
create database jaiskibel;
USE jaiskibel;

CREATE TABLE Usuario(
	dni VARCHAR(9) NOT NULL,
    nombre VARCHAR(25) NOT NULL,
	apellido1 VARCHAR(30) NOT NULL,
    apellido2 VARCHAR(30) NOT NULL,
    sexo ENUM("H","M"),
    contrasena VARCHAR(255) NOT NULL,
    privilegio ENUM("Admin", "Cliente") NOT NULL,
    PRIMARY KEY (dni)
); 

INSERT INTO Usuario VALUES("763497M","Paco","Calleja","Santos","H","PACO1999","Cliente");
INSERT INTO Usuario VALUES("12345678A", "Juan", "Pérez", "García", "H", "12345678", "Admin");
INSERT INTO Usuario VALUES("23456789B", "María", "López", "Fernández", "M", "abc12345", "Cliente");
INSERT INTO Usuario VALUES("34567890C", "Pedro", "Sánchez", "Martín", "H", "p@ssw0rd", "Cliente");
INSERT INTO Usuario VALUES("45678901D", "Laura", "García", "Rodríguez", "M", "qwerty123", "Admin");

CREATE TABLE Establecimiento(
	id SMALLINT AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
	localizacion VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
); 

INSERT INTO Establecimiento (nombre, localizacion) VALUES("Estación de Esquí Baqueira Beret", "Baqueira, Lleida, España");
INSERT INTO Establecimiento (nombre, localizacion) VALUES("Sierra Nevada", "Granada, Andalucía, España");
INSERT INTO Establecimiento (nombre, localizacion) VALUES("Formigal-Panticosa", "Huesca, Aragón, España");
INSERT INTO Establecimiento (nombre, localizacion) VALUES("La Molina", "Girona, Cataluña, España");

CREATE TABLE Reserva(
	id SMALLINT AUTO_INCREMENT,
    idEstablecimiento SMALLINT NOT NULL,
    dniUsuario VARCHAR(9) NOT NULL,
    fechaInicio TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fechaFin TIMESTAMP NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (dniUsuario) REFERENCES Usuario(dni),
	FOREIGN KEY (idEstablecimiento) REFERENCES Establecimiento(id),
    CHECK(fechaFin>=fechaInicio)
); 

INSERT INTO Reserva (dniUsuario, idEstablecimiento, fechaInicio, fechaFin) VALUES("12345678A", 1,"2025-01-01", "2025-12-31");
INSERT INTO Reserva (dniUsuario, idEstablecimiento, fechaInicio, fechaFin) VALUES("23456789B", 1,"2025-01-15", "2025-06-15");
INSERT INTO Reserva (dniUsuario, idEstablecimiento, fechaInicio, fechaFin) VALUES("34567890C", 2,"2025-02-01", "2025-08-01");
INSERT INTO Reserva (dniUsuario, idEstablecimiento, fechaInicio, fechaFin) VALUES("45678901D", 4,"2025-03-10", "2025-09-10");

CREATE TABLE Articulo(
	id SMALLINT AUTO_INCREMENT,
    nombre VARCHAR(100),
	descripcion VARCHAR(300),
    talla ENUM("XS","S","M","XL","L","XXL"),
    precio SMALLINT,
    PRIMARY KEY (id)
); 

INSERT INTO Articulo (nombre, descripcion, talla, precio) VALUES("Esquís All Mountain", "Esquís versátiles ideales para todo tipo de terrenos y condiciones de nieve.", "M", 35);
INSERT INTO Articulo (nombre, descripcion, talla, precio) VALUES("Tabla de Snowboard Freestyle", "Snowboard diseñada para saltos y trucos en el parque de nieve, con un diseño más flexible.", "L", 45);
INSERT INTO Articulo (nombre, descripcion, talla, precio) VALUES("Botas de Esquí Rápidas", "Botas de esquí de alta calidad con cierre rápido y ajuste perfecto para mayor comodidad.", "S", 8);
INSERT INTO Articulo (nombre, descripcion, talla, precio) VALUES("Chaqueta de Esquí Impermeable", "Chaqueta de esquí con aislamiento térmico y tecnología impermeable, ideal para condiciones frías y mojadas.", "XL", 10);
INSERT INTO Articulo (nombre, descripcion, talla, precio) VALUES("Funda para Esquís", "Funda de protección para esquís, ideal para el transporte y almacenamiento, fabricada en material resistente.", "M", 5);
INSERT INTO Articulo (nombre, descripcion, talla, precio) VALUES("Cinta para Gafas de Esquí", "Cinta ajustable para sujetar las gafas de esquí, cómoda y con diseño antideslizante.", "S", 3);
INSERT INTO Articulo (nombre, descripcion, talla, precio) VALUES("Cadenas para Neumáticos", "Cadenas para neumáticos de coche, ideales para circular con seguridad en carreteras nevadas o heladas.", "M", 13);
INSERT INTO Articulo (nombre, descripcion, talla, precio) VALUES("Bolsa Térmica para Botellas", "Bolsa térmica para mantener las bebidas calientes durante el día de esquí, con cierre hermético.", "L", 7);
INSERT INTO Articulo (nombre, descripcion, talla, precio) VALUES("Esquís Racing", "Esquís  ideales para los mejores competidores", "XL", 85);
INSERT INTO Articulo (nombre, descripcion, talla, precio) VALUES("Tabla de Snowboard Competición", "Snowboard diseñada para alcanzar mayor velocidad y maniobraridad.", "L", 75);

CREATE TABLE Ski(
	idArticulo SMALLINT NOT NULL,
    modalidad ENUM("Competicion","Pista","Montaña"),
	nivel ENUM("Principiante","Medio","Avanzado"),
    PRIMARY KEY(idArticulo),
    FOREIGN KEY (idArticulo) REFERENCES Articulo(id)
); 

INSERT INTO Ski (idArticulo, modalidad, nivel) VALUES(1, "Montaña", "medio");
INSERT INTO Ski (idArticulo, modalidad, nivel) VALUES(9, "Competicion", "Avanzado");



CREATE TABLE Snowboard(
	idArticulo SMALLINT NOT NULL,
    modalidad ENUM("Competicion","Pista","Montaña") NOT NULL,
    PRIMARY KEY(idArticulo),
    FOREIGN KEY (idArticulo) REFERENCES Articulo(id)
);

INSERT INTO Snowboard (idArticulo, modalidad) VALUES(2, "Pista");
INSERT INTO Snowboard (idArticulo, modalidad) VALUES(10, "Competicion");


CREATE TABLE Accesorios(
	idArticulo SMALLINT AUTO_INCREMENT,
    tipo VARCHAR(40) DEFAULT("Accesorio"),
    PRIMARY KEY(idArticulo),
    FOREIGN KEY (idArticulo) REFERENCES Articulo(id)
); 

INSERT INTO Accesorios (idArticulo, tipo) VALUES(3, "Botas");
INSERT INTO Accesorios (idArticulo, tipo) VALUES(4, "Chaqueta");
INSERT INTO Accesorios (idArticulo, tipo) VALUES(5, "Funda");
INSERT INTO Accesorios (idArticulo, tipo) VALUES(6, "Cinta");
INSERT INTO Accesorios (idArticulo, tipo) VALUES(7, "Cadenas para Neumáticos");
INSERT INTO Accesorios (idArticulo, tipo) VALUES(8, "Bolsa Térmica para Botellas");


CREATE TABLE articuloEstablecimiento(
	idArticulo SMALLINT NOT NULL,
    idEstablecimiento SMALLINT NOT NULL,
    cantidad SMALLINT NOT NULL CHECK(cantidad>=0),
    PRIMARY KEY (idArticulo, idEstablecimiento),
    FOREIGN KEY (idArticulo) REFERENCES Articulo(id),
    FOREIGN KEY (idEstablecimiento) REFERENCES Establecimiento(id)
); 

INSERT INTO articuloEstablecimiento (idArticulo, idEstablecimiento, cantidad) VALUES(1, 1, 48);
INSERT INTO articuloEstablecimiento (idArticulo, idEstablecimiento, cantidad) VALUES(2, 2, 53);
INSERT INTO articuloEstablecimiento (idArticulo, idEstablecimiento, cantidad) VALUES(3, 3, 65);
INSERT INTO articuloEstablecimiento (idArticulo, idEstablecimiento, cantidad) VALUES(4, 4, 67);
INSERT INTO articuloEstablecimiento (idArticulo, idEstablecimiento, cantidad) VALUES(5, 1, 76);
INSERT INTO articuloEstablecimiento (idArticulo, idEstablecimiento, cantidad) VALUES(6, 2, 114);
INSERT INTO articuloEstablecimiento (idArticulo, idEstablecimiento, cantidad) VALUES(7, 3, 80);
INSERT INTO articuloEstablecimiento (idArticulo, idEstablecimiento, cantidad) VALUES(8, 4, 93);
INSERT INTO articuloEstablecimiento (idArticulo, idEstablecimiento, cantidad) VALUES(9, 2, 55);
INSERT INTO articuloEstablecimiento (idArticulo, idEstablecimiento, cantidad) VALUES(9, 1, 68);
INSERT INTO articuloEstablecimiento (idArticulo, idEstablecimiento, cantidad) VALUES(9, 3, 48);
INSERT INTO articuloEstablecimiento (idArticulo, idEstablecimiento, cantidad) VALUES(9, 4, 32);
INSERT INTO articuloEstablecimiento (idArticulo, idEstablecimiento, cantidad) VALUES(10, 4, 63);
INSERT INTO articuloEstablecimiento (idArticulo, idEstablecimiento, cantidad) VALUES(10, 3, 56);
INSERT INTO articuloEstablecimiento (idArticulo, idEstablecimiento, cantidad) VALUES(10, 2, 76);
INSERT INTO articuloEstablecimiento (idArticulo, idEstablecimiento, cantidad) VALUES(10, 1, 48);

CREATE TABLE articuloReservado(
	idReserva SMALLINT NOT NULL,
    idArticulo SMALLINT NOT NULL,
    cantidad SMALLINT NOT NULL CHECK(cantidad>=0),
    PRIMARY KEY (idReserva, idArticulo),
    FOREIGN KEY (idReserva) REFERENCES Reserva(id),
    FOREIGN KEY (idArticulo) REFERENCES Articulo(id)
); 

INSERT INTO articuloReservado (idReserva, idArticulo, cantidad) VALUES(1, 1, 11);
INSERT INTO articuloReservado (idReserva, idArticulo, cantidad) VALUES(2, 2, 15);
INSERT INTO articuloReservado (idReserva, idArticulo, cantidad) VALUES(3, 3, 19);
INSERT INTO articuloReservado (idReserva, idArticulo, cantidad) VALUES(4, 4, 28);
INSERT INTO articuloReservado (idReserva, idArticulo, cantidad) VALUES(1, 5, 10);
INSERT INTO articuloReservado (idReserva, idArticulo, cantidad) VALUES(2, 6, 14);
INSERT INTO articuloReservado (idReserva, idArticulo, cantidad) VALUES(3, 7, 34);
INSERT INTO articuloReservado (idReserva, idArticulo, cantidad) VALUES(4, 8, 37);
INSERT INTO articuloReservado (idReserva, idArticulo, cantidad) VALUES(2, 9, 27);
INSERT INTO articuloReservado (idReserva, idArticulo, cantidad) VALUES(3, 10, 20);