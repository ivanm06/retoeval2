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
    privilegio ENUM("Admin", "Cliente") NOT NULL DEFAULT "Cliente",
    PRIMARY KEY (dni)
); 

INSERT INTO Usuario VALUES("763497M","Paco","Calleja","Santos","H","$2a$10$519yFlebqWaY0q1L2tpJnuERijidRd0DLQM7EONIMBF3zGRWmX5SS","Cliente");
INSERT INTO Usuario VALUES("12345678A", "Juan", "Pérez", "García", "H", "$2a$10$519yFlebqWaY0q1L2tpJnuERijidRd0DLQM7EONIMBF3zGRWmX5SS", "Admin");
INSERT INTO Usuario VALUES("23456789B", "María", "López", "Fernández", "M", "$2a$10$519yFlebqWaY0q1L2tpJnuERijidRd0DLQM7EONIMBF3zGRWmX5SS", "Cliente");
INSERT INTO Usuario VALUES("34567890C", "Pedro", "Sánchez", "Martín", "H", "$2a$10$519yFlebqWaY0q1L2tpJnuERijidRd0DLQM7EONIMBF3zGRWmX5SS", "Cliente");
INSERT INTO Usuario VALUES("45678901D", "Laura", "García", "Rodríguez", "M", "$2a$10$519yFlebqWaY0q1L2tpJnuERijidRd0DLQM7EONIMBF3zGRWmX5SS", "Admin");
INSERT INTO Usuario VALUES("49580251W", "Ivan", "Martin", "Rivas", "H", "$2a$10$xCOMkdedEz04R86kNN.MK.HK55XADgb0CeiH3XWq5l0Yd6.EOV72e", "Admin");

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

INSERT INTO Reserva (dniUsuario, idEstablecimiento, fechaInicio, fechaFin) VALUES("12345678A", 1,"2025-01-01", "2025-01-05");
INSERT INTO Reserva (dniUsuario, idEstablecimiento, fechaInicio, fechaFin) VALUES("23456789B", 1,"2025-01-20", "2025-01-23");
INSERT INTO Reserva (dniUsuario, idEstablecimiento, fechaInicio, fechaFin) VALUES("34567890C", 2,"2025-02-01", "2025-02-06");
INSERT INTO Reserva (dniUsuario, idEstablecimiento, fechaInicio, fechaFin) VALUES("45678901D", 4,"2025-03-10", "2025-03-11");

CREATE TABLE Articulo(
	id SMALLINT AUTO_INCREMENT,
    nombre VARCHAR(100),
	descripcion VARCHAR(300),
    talla ENUM("XS","S","M","XL","L","XXL"),
    precio SMALLINT,
    PRIMARY KEY (id)
); 

INSERT INTO Articulo (nombre, descripcion, talla, precio) VALUES("Esquís All Mountain", "Esquís versátiles ideales para todo tipo de terrenos y condiciones de nieve.", "M", 20);
INSERT INTO Articulo (nombre, descripcion, talla, precio) VALUES("Tabla de Snowboard Freestyle", "Snowboard diseñada para saltos y trucos en el parque de nieve, con un diseño más flexible.", "L", 25);
INSERT INTO Articulo (nombre, descripcion, talla, precio) VALUES("Botas de Esquí Rápidas", "Botas de esquí de alta calidad con cierre rápido y ajuste perfecto para mayor comodidad.", "S", 8);
INSERT INTO Articulo (nombre, descripcion, talla, precio) VALUES("Chaqueta de Esquí Impermeable", "Chaqueta de esquí con aislamiento térmico y tecnología impermeable, ideal para condiciones frías y mojadas.", "XL", 10);
INSERT INTO Articulo (nombre, descripcion, talla, precio) VALUES("Funda para Esquís", "Funda de protección para esquís, ideal para el transporte y almacenamiento, fabricada en material resistente.", "M", 4);
INSERT INTO Articulo (nombre, descripcion, talla, precio) VALUES("Cinta para Gafas de Esquí", "Cinta ajustable para sujetar las gafas de esquí, cómoda y con diseño antideslizante.", "S", 2);
INSERT INTO Articulo (nombre, descripcion, talla, precio) VALUES("Cadenas para Neumáticos", "Cadenas para neumáticos de coche, ideales para circular con seguridad en carreteras nevadas o heladas.", "M", 8);
INSERT INTO Articulo (nombre, descripcion, talla, precio) VALUES("Bolsa Térmica para Botellas", "Bolsa térmica para mantener las bebidas calientes durante el día de esquí, con cierre hermético.", "L", 5);
INSERT INTO Articulo (nombre, descripcion, talla, precio) VALUES("Esquís Racing", "Esquís  ideales para los mejores competidores", "XL", 40);
INSERT INTO Articulo (nombre, descripcion, talla, precio) VALUES("Tabla de Snowboard Competición", "Snowboard diseñada para alcanzar mayor velocidad y maniobraridad.", "L", 35);

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


-- Extra

INSERT INTO Articulo (nombre, descripcion, talla, precio) VALUES
("Guantes térmicos", "Guantes con aislamiento térmico y resistencia al agua para el esquí.", "M", 5),
("Casco de esquí", "Casco ligero y resistente para mayor seguridad en la nieve.", "L", 12),
("Gafas de esquí antivaho", "Gafas con tecnología antivaho y protección UV.", "S", 8),
("Pantalones de esquí", "Pantalones impermeables y transpirables para esquiar con comodidad.", "XL", 15),
("Calcetines térmicos", "Calcetines con tecnología de retención de calor para temperaturas extremas.", "M", 4),
("Mochila para nieve", "Mochila con compartimentos especiales para equipo de esquí y snowboard.", "L", 10),
("Rodilleras de protección", "Rodilleras acolchonadas para amortiguar impactos en la nieve.", "M", 6),
("Camiseta térmica", "Camiseta de manga larga con tejido térmico para mantener el calor corporal.", "S", 7),
("Polainas para nieve", "Polainas impermeables para evitar la entrada de nieve en las botas.", "M", 5),
("Cera para esquís", "Cera especial para mejorar el deslizamiento de los esquís en la nieve.", "S", 3),
("Bastones de esquí", "Bastones ligeros y ergonómicos para mejorar la estabilidad en el esquí.", "M", 10),
("Protector de espalda", "Protector con diseño ergonómico para prevenir lesiones en caídas.", "L", 15),
("Botella térmica", "Botella de acero inoxidable con aislamiento térmico para bebidas calientes.", "S", 6),
("Bufanda polar", "Bufanda suave y térmica para protegerse del frío en la nieve.", "M", 3),
("Mono de esquí", "Traje completo impermeable y térmico para protección máxima en la nieve.", "XL", 25),
("Crampones para nieve", "Crampones antideslizantes para caminar con seguridad en superficies heladas.", "L", 10),
("Gorro de lana", "Gorro ajustado y térmico para mantener la cabeza caliente.", "M", 3),
("Forro polar", "Chaqueta con forro polar para mayor abrigo en condiciones de frío extremo.", "XL", 12),
("Linterna frontal", "Linterna con ajuste para la cabeza, ideal para condiciones de poca luz.", "S", 6),
("Cinturón portaobjetos", "Cinturón con bolsillos para llevar objetos esenciales en la nieve.", "M", 5),
("Esquís Freeride", "Esquís diseñados para máxima flotabilidad en nieve profunda.", "L", 35),
("Esquís de travesía", "Esquís ligeros para subir montañas con pieles de foca y descender con control.", "M", 30),
("Snowboard All-Mountain", "Tabla versátil para todo tipo de terrenos y estilos de conducción.", "L", 28),
("Snowboard Freeride", "Snowboard diseñado para nieve polvo y terrenos escarpados.", "XL", 20);

-- Inserción de accesorios en la tabla Accesorios
INSERT INTO Accesorios (idArticulo, tipo) VALUES
((SELECT id FROM Articulo WHERE nombre = "Guantes térmicos"), "Guantes"),
((SELECT id FROM Articulo WHERE nombre = "Casco de esquí"), "Casco"),
((SELECT id FROM Articulo WHERE nombre = "Gafas de esquí antivaho"), "Gafas"),
((SELECT id FROM Articulo WHERE nombre = "Pantalones de esquí"), "Pantalones"),
((SELECT id FROM Articulo WHERE nombre = "Calcetines térmicos"), "Calcetines"),
((SELECT id FROM Articulo WHERE nombre = "Mochila para nieve"), "Mochila"),
((SELECT id FROM Articulo WHERE nombre = "Rodilleras de protección"), "Rodilleras"),
((SELECT id FROM Articulo WHERE nombre = "Camiseta térmica"), "Camiseta térmica"),
((SELECT id FROM Articulo WHERE nombre = "Polainas para nieve"), "Polainas"),
((SELECT id FROM Articulo WHERE nombre = "Cera para esquís"), "Cera"),
((SELECT id FROM Articulo WHERE nombre = "Bastones de esquí"), "Bastones"),
((SELECT id FROM Articulo WHERE nombre = "Protector de espalda"), "Protector de espalda"),
((SELECT id FROM Articulo WHERE nombre = "Botella térmica"), "Botella térmica"),
((SELECT id FROM Articulo WHERE nombre = "Bufanda polar"), "Bufanda"),
((SELECT id FROM Articulo WHERE nombre = "Mono de esquí"), "Mono de esquí"),
((SELECT id FROM Articulo WHERE nombre = "Crampones para nieve"), "Crampones"),
((SELECT id FROM Articulo WHERE nombre = "Gorro de lana"), "Gorro"),
((SELECT id FROM Articulo WHERE nombre = "Forro polar"), "Forro polar"),
((SELECT id FROM Articulo WHERE nombre = "Linterna frontal"), "Linterna"),
((SELECT id FROM Articulo WHERE nombre = "Cinturón portaobjetos"), "Cinturón");

-- Inserción de esquís en la tabla Ski
INSERT INTO Ski (idArticulo, modalidad, nivel) VALUES
((SELECT id FROM Articulo WHERE nombre = "Esquís Freeride"), "Montaña", "Avanzado"),
((SELECT id FROM Articulo WHERE nombre = "Esquís de travesía"), "Montaña", "Medio");

-- Inserción de snowboards en la tabla Snowboard
INSERT INTO Snowboard (idArticulo, modalidad) VALUES
((SELECT id FROM Articulo WHERE nombre = "Snowboard All-Mountain"), "Pista"),
((SELECT id FROM Articulo WHERE nombre = "Snowboard Freeride"), "Montaña");

INSERT INTO articuloEstablecimiento (idArticulo, idEstablecimiento, cantidad) VALUES
(11, 1, 10),  -- Guantes térmicos
(12, 1, 10),  -- Casco de esquí
(13, 1, 10),  -- Gafas de esquí antivaho
(14, 1, 10),  -- Pantalones de esquí
(15, 1, 10),  -- Calcetines térmicos
(16, 1, 10),  -- Mochila para nieve
(17, 1, 10),  -- Rodilleras de protección
(18, 1, 10),  -- Camiseta térmica
(19, 1, 10),  -- Polainas para nieve
(20, 1, 10), -- Cera para esquís
(21, 1, 10), -- Bastones de esquí
(22, 1, 10), -- Protector de espalda
(23, 1, 10), -- Botella térmica
(24, 1, 10), -- Bufanda polar
(25, 1, 10), -- Mono de esquí
(26, 1, 10), -- Crampones para nieve
(27, 1, 10), -- Gorro de lana
(28, 1, 10), -- Forro polar
(29, 1, 10), -- Linterna frontal
(30, 1, 10), -- Cinturón portaobjetos
(31, 1, 10), -- Esquís Freeride
(32, 1, 10), -- Esquís de travesía
(33, 1, 10), -- Snowboard All-Mountain
(34, 1, 10); -- Snowboard Freeride