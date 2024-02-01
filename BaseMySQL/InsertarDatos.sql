-- Insertar datos en la tabla tribu
USE yourminifactory;
-- Insertar datos en la tabla Categoria
INSERT INTO Categoria (id_categoria, nombre) VALUES
    (1, 'Categoría 1'),
    (2, 'Categoría 2'),
    (3, 'Categoría 3'),
    (4, 'Categoría 4'),
    (5, 'Categoría 5'),
    (6, 'Categoría 6'),
    (7, 'Categoría 7'),
    (8, 'Categoría 8'),
    (9, 'Categoría 9'),
    (10, 'Categoría 10'),
    (11, 'Categoría 11'),
    (12, 'Categoría 12'),
    (13, 'Categoría 13'),
    (14, 'Categoría 14'),
    (15, 'Categoría 15'),
    (16, 'Categoría 16'),
    (17, 'Categoría 17'),
    (18, 'Categoría 18'),
    (19, 'Categoría 19'),
    (20, 'Categoría 20');
    
INSERT INTO tribu (descripcion, name, num_pioneros, id_categoria) 
VALUES 
    ('Miniatures', 'Witchsong', 6892, 1),
    ('Roleplaying Games', 'Big Bad Evil Guy', 2409, 2),
    ('High-Quality Prints', 'Lord of the Print', 2399, 3),
    ('Fantasy Adventures', 'The Witchguild', 2210, 4),
    ('Detailed Miniature Collections', 'Resinant Miniatures', 48, 5),
    ('Stylized Fantasy Figures', 'Velrock Art Miniatures', 175, 6),
    ('Epic Scale Miniatures', 'Lord of the Print', 2399, 7),
    ('Innovative Game Miniatures', 'Infinite Dimensions Games', 507, 8),
    ('Custom Designed Miniatures', 'Josh', 24, 9),
    ('Dark Fantasy World Creations', 'Dark Realms Forge', 121, 10),
    ('Mythical Creature Sculptures', 'Little Shop of Sigil', 6, 11),
    ('Historical Miniature Scenes', 'Nikita Breder', 26, 12),
    ('Mystical Landscapes', 'Echoes of Eldoria', 3120, 13),
    ('Space Odyssey Models', 'Galactic Frontier', 867, 14),
    ('Ancient Civilization Artifacts', 'Timeless Relics', 1543, 15),
    ('Steampunk Gadgets', 'Cog & Gear Creations', 982, 16),
    ('Futuristic Cityscapes', 'Neo-Urban Legends', 645, 17),
    ('Cyberpunk Chronicles', 'Neo-Tokyo Drifters', 1345, 18),
    ('Underwater Exploration Models', 'Deep Sea Discoverers', 789, 19),
    ('Mythical Legends Artwork', 'Mythic Artisans', 532, 20),
    ('Interactive Puzzle Games', 'Brain Teaser Buffs', 874, 20),
    ('Enchanted Forest Miniatures', 'Greenwood Mystics', 208, 2);
    
-- Insertar datos en la tabla tier
INSERT INTO tier (precioMensaul,nivelApoyo, descuento,recompensa) VALUES
	(50,1, 5,'NIVEL 1'),
	(100,2, 10,'NIVEL 2'),
	(150,3,15, 'NIVEL 3'),
	(200,4,20, 'NIVEL 4'),
	(250,5,25, 'NIVEL 5');

-- Insertar datos en la tabla campana
INSERT INTO campana (descripcion, pioneros, dinerorecaudado) VALUES
    ('Campaña 1: Lanzamiento de Producto A', 500, 1500.0),
    ('Campaña 2: Apoyo a la Comunidad B', 300, 900.0),
    ('Campaña 3: Proyecto de Innovación C', 800, 2400.0),
    ('Campaña 4: Arte y Cultura D', 200, 600.0),
    ('Campaña 5: Educación E', 600, 1800.0),
    ('Campaña 6: Arte Abstracto', 400, 1200.0);

-- Insertar datos en la tabla usuario
INSERT INTO usuario (nombre, fecha_nacimi, contrasena, correo) VALUES
('Juan Pérez', '1995-03-15', 'contraseña1', 'juan@example.com'),
('María López', '1988-07-20', 'contraseña2', 'maria@example.com'),
('Carlos Rodríguez', '1990-05-10', 'contraseña3', 'carlos@example.com'),
('Ana García', '1993-12-28', 'contraseña4', 'ana@example.com'),
('Luis Fernández', '1985-09-03', 'contraseña5', 'luis@example.com'),
('Laura Martínez', '1994-08-21', 'contraseña6', 'laura@example.com'),
('Pedro Sánchez', '1987-06-05', 'contraseña7', 'pedro@example.com'),
('Isabel Pérez', '1992-04-12', 'contraseña8', 'isabel@example.com'),
('Miguel López', '1996-01-30', 'contraseña9', 'miguel@example.com'),
('Elena Rodríguez', '1989-10-09', 'contraseña10', 'elena@example.com'),
('Lorenzo Ramírez', '1991-02-18', 'contraseña11', 'lorenzo@example.com'),
('Sofía González', '1997-09-05', 'contraseña12', 'sofia@example.com'),
('Diego Herrera', '1998-06-28', 'contraseña13', 'diego@example.com'),
('Carmen Silva', '1986-12-10', 'contraseña14', 'carmen@example.com'),
('Javier Jiménez', '1994-03-24', 'contraseña15', 'javier@example.com'),
('Elena Martínez', '1992-04-12', 'contraseña16', 'elena@example.com'),
('Pedro García', '1996-08-25', 'contraseña17', 'pedro@example.com'),
('Laura Rodríguez', '1989-11-30', 'contraseña18', 'laura@example.com'),
('Miguel Fernández', '1997-07-05', 'contraseña19', 'miguel@example.com'),
('Isabel Pérez', '1993-03-18', 'contraseña20', 'isabel@example.com');

-- Insertar datos en la tabla Apoya
INSERT INTO Apoya (id_usuario, id_campana,nivel) VALUES
    (1, 1,1),
    (2, 2,1),
    (3, 3,1),
    (4, 4,1),
    (5, 5,1),
    (6, 6,1),
    (8, 2,1),
    (9, 3,1),
    (10, 1,1),
    (11, 1,1),
    (12, 2,1),
    (13, 3,1),
    (14, 4,1),
    (15, 5,1),
    (16, 6,1),
    (17, 3,1),
    (18, 2,1),
    (19, 3,1);
    
-- Insertar datos en la tabla libreria
INSERT INTO libreria (nombre, id_usuario) VALUES
    ('Mi Librería 1', 1),
    ('Colección Personal 2', 2),
    ('Librería Digital 3', 3),
    ('Arte en Casa 4', 4),
    ('Modelos 3D 5', 5),
    ('Colección de Obras 6', 6),
    ('Librería Creativa 7', 7),
    ('Arte en Línea 8', 8),
    ('Diseños Únicos 9', 9),
    ('Modelos Avanzados 10', 10),
    ('Colección Premium 11', 11),
    ('Arte en Casa 12', 12),
    ('Libros Digitales 13', 13),
    ('Diseños Exclusivos 14', 14),
    ('Modelos Creativos 15', 15),
    ('Colección de Éxito 16', 16),
    ('Arte en Casa 17', 17),
    ('Biblioteca Digital 18', 18),
    ('Diseños Exclusivos 19', 19),
    ('Modelos Creativos 20', 20);

-- Insertar datos en la tabla modelo
INSERT INTO modelo (descripcion, precio, titulo, model, fecha_publicacion, id_libreria) VALUES
    ('Modelo 3D Impresionante', 50, 'Estatua de Dragón', 'dragon.stl', '2023-03-01', 1),
    ('Obra de Arte Moderno', 100, 'Paisaje Urbano', 'paisaje.jpg', '2023-04-15', 2),
    ('Diseño de Producto', 80, 'Lámpara Minimalista', 'lamp.obj', '2023-02-10', 3),
    ('Modelo 3D Creativo', 60, 'Figura Abstracta', 'abstracto.stl', '2023-03-20', 4),
    ('Escultura Realista', 120, 'Retrato de Persona', 'retrato.obj', '2023-05-05', 5),
    ('Escultura Fantástica', 70, 'Dragón Mágico', 'dragon2.stl', '2015-07-10', 6),
    ('Pintura al Óleo', 120, 'Retrato de Paisaje', 'paisaje2.jpg', '2016-05-15', 7),
    ('Diseño Innovador', 90, 'Lámpara de Diseño', 'lamp2.obj', '2017-03-20', 8),
    ('Arte Abstracto', 50, 'Figura Colorida', 'abstracto2.stl', '2018-09-25', 9),
    ('Escultura en Madera', 80, 'Escultura en Madera', 'madera.obj', '2019-11-30', 10),
    ('Escultura Abstracta', 70, 'Obra de Arte Abstracta', 'abstracto3.stl', '2015-08-15', 11),
    ('Pintura en Óleo', 130, 'Retrato Clásico', 'retrato2.jpg', '2016-06-20', 12),
    ('Diseño de Producto Elegante', 95, 'Lámpara de Lujo', 'lamp3.obj', '2017-04-25', 13),
    ('Escultura en Bronce', 60, 'Figura Esculpida', 'escultura.obj', '2018-10-30', 14),
    ('Arte Digital', 85, 'Arte Digital Colorido', 'arte.jpg', '2019-12-15', 15),
    ('Escultura Contemporánea', 75, 'Obra de Arte Contemporáneo', 'contemporaneo.stl', '2015-12-20', 16),
    ('Pintura al Óleo', 140, 'Retrato Moderno', 'retrato3.jpg', '2016-11-15', 17),
    ('Diseño Elegante', 100, 'Lámpara de Diseño', 'lamp4.obj', '2017-09-10', 18),
    ('Escultura en Mármol', 70, 'Figura en Mármol', 'marmol.obj', '2018-08-05', 19),
    ('Arte Digital Abstracto', 90, 'Arte Abstracto en Digital', 'abstracto4.jpg', '2019-07-30', 20);

-- Insertar datos en la tabla carro_compra
INSERT INTO carro_compra (id_usuario, total, fecha_pago, descripcion, state) VALUES
    (1, 100.0, '2023-03-05', 'Compra de modelos 3D', TRUE),
    (2, 150.0, '2023-04-10', 'Colección de arte', TRUE),
    (3, 200.0, '2023-02-15', 'Diseños exclusivos', FALSE),
    (4, 250.0, '2023-05-20', 'Esculturas personalizadas', TRUE),
    (5, 300.0, '2023-03-25', 'Arte realista', FALSE),
    (6, 90.0, '2015-12-12', 'Compra de modelos 3D', TRUE),
    (7, 130.0, '2016-08-20', 'Colección de arte', FALSE),
    (8, 180.0, '2017-04-25', 'Diseños exclusivos', TRUE),
    (9, 220.0, '2018-11-30', 'Esculturas personalizadas', FALSE),
    (10, 260.0, '2019-10-15', 'Arte realista', TRUE),
    (11, 110.0, '2015-10-12', 'Compra de arte', FALSE),
    (12, 160.0, '2016-07-20', 'Colección de modelos 3D', TRUE),
    (13, 220.0, '2017-03-25', 'Esculturas únicas', FALSE),
    (14, 280.0, '2018-11-30', 'Diseños personalizados', TRUE),
    (15, 320.0, '2019-09-30', 'Arte contemporáneo', FALSE),
    (16, 120.0, '2015-06-10', 'Compra de arte contemporáneo', TRUE),
    (17, 170.0, '2016-04-15', 'Colección de pinturas', FALSE),
    (18, 240.0, '2017-02-20', 'Diseños exclusivos de lámparas', TRUE),
    (19, 290.0, '2018-10-25', 'Esculturas en mármol personalizadas', FALSE),
    (20, 330.0, '2019-09-30', 'Arte digital abstracto', TRUE);

-- Insertar datos en la tabla anadir
INSERT INTO anadir (id_carrito, id_modelo) VALUES
	(1,2),
    (1,6),
    (1,20),
    (2,1),
    (3,6),
    (4,20),
	(1,14),
    (1,13),
    (2,4),
    (2,5),
    (2,20),
    (3,9),
    (3,1),
    (3,8),
    (3,11),
    (3,12),
    (1,15),
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9),
    (10, 10),
    (11, 11),
    (12, 12),
    (13, 13),
    (14, 14),
    (15, 15),
    (16, 16),
    (17, 17),
    (18, 18),
    (19, 19),
    (20, 20);
    
-- Insertar datos en la tabla Asignación  
INSERT INTO Asignacion (id_campana, id_tier, id_modelo) VALUES
	(1, 1, 17),
	(1, 1, 16),
	(1, 2, 4),
	(1, 3, 9),
	(1, 4, 15),
	(1, 5, 3),
	(2, 1, 20),
	(2, 2, 8),
	(2, 3, 11),
	(2, 4, 5),
	(2, 5, 14),
	(3, 1, 2),
	(3, 2, 7),
	(3, 3, 13),
	(3, 4, 6),
	(3, 5, 1),
	(4, 1, 10),
	(4, 2, 18),
	(4, 3, 12),
	(4, 4, 16),
	(4, 5, 19),
	(5, 1, 7),
	(5, 2, 11),
	(5, 3, 4),
	(5, 4, 9),
	(5, 5, 2);

Update modelo 
set visibilidad = True
where id_modelo>= 0;