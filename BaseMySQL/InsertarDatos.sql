
-- Insertar datos en la tabla tribu
INSERT INTO tribu (descripcion) VALUES
    ('Tribu A'),
    ('Tribu B'),
    ('Tribu C'),
    ('Tribu D'),
    ('Tribu E');

-- Insertar datos en la tabla Categoria
INSERT INTO Categoria (id_categoria, nombre, id_tribu) VALUES
    (1, 'Categoría 1', 1),
    (2, 'Categoría 2', 2),
    (3, 'Categoría 3', 3),
    (4, 'Categoría 4', 4),
    (5, 'Categoría 5', 5);

-- Insertar datos en la tabla tier
INSERT INTO tier (nivelApoyo, recompensa) VALUES
    (1, 'Agradecimiento en la página web'),
    (2, 'Mención especial en redes sociales'),
    (3, 'Acceso exclusivo a contenido'),
    (4, 'Producto personalizado'),
    (5, 'Invitación a evento especial');

-- Insertar datos en la tabla campana
INSERT INTO campana (descricipcion, pioneros, comentarios, dinerorecaudado) VALUES
    ('Campaña 1: Lanzamiento de Producto A', 500, '¡Excelente proyecto!', 1500.0),
    ('Campaña 2: Apoyo a la Comunidad B', 300, 'Seguimos creciendo juntos', 900.0),
    ('Campaña 3: Proyecto de Innovación C', 800, 'Gracias por ser parte de esto', 2400.0),
    ('Campaña 4: Arte y Cultura D', 200, 'El arte nos une', 600.0),
    ('Campaña 5: Educación E', 600, 'Juntos construimos el futuro', 1800.0);

-- Insertar datos en la tabla usuario
INSERT INTO usuario (nombre, fecha_nacimi, contrasena, correo, id_tribu) VALUES
    ('Juan Pérez', '1995-03-15', 'contraseña1', 'juan@example.com', 1),
    ('María López', '1988-07-20', 'contraseña2', 'maria@example.com', 2),
    ('Carlos Rodríguez', '1990-05-10', 'contraseña3', 'carlos@example.com', 3),
    ('Ana García', '1993-12-28', 'contraseña4', 'ana@example.com', 4),
    ('Luis Fernández', '1985-09-03', 'contraseña5', 'luis@example.com', 5);

-- Insertar datos en la tabla Apoya
INSERT INTO Apoya (id_usuario, id_campana) VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5);

-- Insertar datos en la tabla libreria
INSERT INTO libreria (nombre, id_usuario) VALUES
    ('Mi Librería 1', 1),
    ('Colección Personal 2', 2),
    ('Librería Digital 3', 3),
    ('Arte en Casa 4', 4),
    ('Modelos 3D 5', 5);

-- Insertar datos en la tabla modelo
INSERT INTO modelo (descripcion, precio, titulo, model, fecha_publicacion, id_usuario, id_libreria) VALUES
    ('Modelo 3D Impresionante', 50, 'Estatua de Dragón', 'dragon.stl', '2023-03-01', 1, 1),
    ('Obra de Arte Moderno', 100, 'Paisaje Urbano', 'paisaje.jpg', '2023-04-15', 2, 2),
    ('Diseño de Producto', 80, 'Lámpara Minimalista', 'lamp.obj', '2023-02-10', 3, 3),
    ('Modelo 3D Creativo', 60, 'Figura Abstracta', 'abstracto.stl', '2023-03-20', 4, 4),
    ('Escultura Realista', 120, 'Retrato de Persona', 'retrato.obj', '2023-05-05', 5, 5);

-- Insertar datos en la tabla etiqueta
INSERT INTO etiqueta (nombre, id_modelo) VALUES
    ('Escultura', 1),
    ('Arte', 2),
    ('Diseño', 3),
    ('Abstracto', 4),
    ('Retrato', 5);

-- Insertar datos en la tabla contiene
INSERT INTO contiene (id_modelo, id_Tier) VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5);

-- Insertar datos en la tabla carro_compra
INSERT INTO carro_compra (id_usuario, total, fecha_pago, descripcion) VALUES
    (1, 100.0, '2023-03-05', 'Compra de modelos 3D'),
    (2, 150.0, '2023-04-10', 'Colección de arte'),
    (3, 200.0, '2023-02-15', 'Diseños exclusivos'),
    (4, 250.0, '2023-05-20', 'Esculturas personalizadas'),
    (5, 300.0, '2023-03-25', 'Arte realista');

-- Insertar datos en la tabla anadir
INSERT INTO anadir (id_carrito, id_modelo) VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5);
-- Insertar datos adicionales en la tabla tribu
INSERT INTO tribu (descripcion) VALUES
    ('Tribu F'),
    ('Tribu G'),
    ('Tribu H'),
    ('Tribu I'),
    ('Tribu J');

-- Insertar datos adicionales en la tabla Categoria
INSERT INTO Categoria (id_categoria, nombre, id_tribu) VALUES
    (6, 'Categoría 6', 6),
    (7, 'Categoría 7', 7),
    (8, 'Categoría 8', 8),
    (9, 'Categoría 9', 9),
    (10, 'Categoría 10', 10);

-- Insertar datos adicionales en la tabla tier
INSERT INTO tier (nivelApoyo, recompensa) VALUES
    (6, 'Mención en evento en vivo'),
    (7, 'Producto exclusivo firmado'),
    (8, 'Invitación a reunión VIP'),
    (9, 'Acceso a prototipos exclusivos'),
    (10, 'Reconocimiento especial en la comunidad');

-- Insertar datos adicionales en la tabla campana
INSERT INTO campana (descricipcion, pioneros, comentarios, dinerorecaudado) VALUES
    ('Campaña 6: Arte Abstracto', 400, 'Un viaje al mundo del arte', 1200.0),
    ('Campaña 7: Educación STEM', 700, 'Fomentando la ciencia', 2100.0),
    ('Campaña 8: Tecnología Innovadora', 600, 'Construyendo el futuro', 1800.0),
    ('Campaña 9: Diseño Sostenible', 300, 'Cuidando nuestro planeta', 900.0),
    ('Campaña 10: Comunidad Creativa', 800, 'Inspiración y creatividad', 2400.0);

-- Insertar datos adicionales en la tabla usuario
INSERT INTO usuario (nombre, fecha_nacimi, contrasena, correo, id_tribu) VALUES
    ('Laura Martínez', '1994-08-21', 'contraseña6', 'laura@example.com', 6),
    ('Pedro Sánchez', '1987-06-05', 'contraseña7', 'pedro@example.com', 7),
    ('Isabel Pérez', '1992-04-12', 'contraseña8', 'isabel@example.com', 8),
    ('Miguel López', '1996-01-30', 'contraseña9', 'miguel@example.com', 9),
    ('Elena Rodríguez', '1989-10-09', 'contraseña10', 'elena@example.com', 10);

-- Insertar datos adicionales en la tabla Apoya
INSERT INTO Apoya (id_usuario, id_campana) VALUES
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9),
    (10, 10);

-- Insertar datos adicionales en la tabla libreria
INSERT INTO libreria (nombre, id_usuario) VALUES
    ('Colección de Obras 6', 6),
    ('Librería Creativa 7', 7),
    ('Arte en Línea 8', 8),
    ('Diseños Únicos 9', 9),
    ('Modelos Avanzados 10', 10);

-- Insertar datos adicionales en la tabla modelo con fechas desde 2015
INSERT INTO modelo (descripcion, precio, titulo, model, fecha_publicacion, id_usuario, id_libreria) VALUES
    ('Escultura Fantástica', 70, 'Dragón Mágico', 'dragon2.stl', '2015-07-10', 6, 6),
    ('Pintura al Óleo', 120, 'Retrato de Paisaje', 'paisaje2.jpg', '2016-05-15', 7, 7),
    ('Diseño Innovador', 90, 'Lámpara de Diseño', 'lamp2.obj', '2017-03-20', 8, 8),
    ('Arte Abstracto', 50, 'Figura Colorida', 'abstracto2.stl', '2018-09-25', 9, 9),
    ('Escultura en Madera', 80, 'Escultura en Madera', 'madera.obj', '2019-11-30', 10, 10);

-- Insertar datos adicionales en la tabla etiqueta
INSERT INTO etiqueta (nombre, id_modelo) VALUES
    ('Fantasía', 6),
    ('Paisaje', 7),
    ('Diseño', 8),
    ('Abstracto', 9),
    ('Madera', 10);

-- Insertar datos adicionales en la tabla contiene
INSERT INTO contiene (id_modelo, id_Tier) VALUES
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9),
    (10, 10);

-- Insertar datos adicionales en la tabla carro_compra con fechas desde 2015
INSERT INTO carro_compra (id_usuario, total, fecha_pago, descripcion) VALUES
    (6, 90.0, '2015-12-12', 'Compra de modelos 3D'),
    (7, 130.0, '2016-08-20', 'Colección de arte'),
    (8, 180.0, '2017-04-25', 'Diseños exclusivos'),
    (9, 220.0, '2018-11-30', 'Esculturas personalizadas'),
    (10, 260.0, '2019-10-15', 'Arte realista');





-- Insertar datos adicionales en la tabla tribu
INSERT INTO tribu (descripcion) VALUES
    ('Tribu K'),
    ('Tribu L'),
    ('Tribu M'),
    ('Tribu N'),
    ('Tribu O');

-- Insertar datos adicionales en la tabla Categoria
INSERT INTO Categoria (id_categoria, nombre, id_tribu) VALUES
    (11, 'Categoría 11', 11),
    (12, 'Categoría 12', 12),
    (13, 'Categoría 13', 13),
    (14, 'Categoría 14', 14),
    (15, 'Categoría 15', 15);

-- Insertar datos adicionales en la tabla tier
INSERT INTO tier (nivelApoyo, recompensa) VALUES
    (11, 'Agradecimiento especial'),
    (12, 'Acceso exclusivo a eventos'),
    (13, 'Producto personalizado y firmado'),
    (14, 'Invitación a viaje exclusivo'),
    (15, 'Reconocimiento VIP en la comunidad');

-- Insertar datos adicionales en la tabla campana
INSERT INTO campana (descricipcion, pioneros, comentarios, dinerorecaudado) VALUES
    ('Campaña 11: Innovación Tecnológica', 450, 'Construyendo el futuro', 1350.0),
    ('Campaña 12: Arte y Cultura', 250, 'Promoviendo la creatividad', 750.0),
    ('Campaña 13: Educación de Calidad', 600, 'Apoyando la educación', 1800.0),
    ('Campaña 14: Diseño Sostenible', 350, 'Por un mundo mejor', 1050.0),
    ('Campaña 15: Comunidad Creativa', 700, 'Celebrando la diversidad', 2100.0);

-- Insertar datos adicionales en la tabla usuario
INSERT INTO usuario (nombre, fecha_nacimi, contrasena, correo, id_tribu) VALUES
    ('Lorenzo Ramírez', '1991-02-18', 'contraseña11', 'lorenzo@example.com', 11),
    ('Sofía González', '1997-09-05', 'contraseña12', 'sofia@example.com', 12),
    ('Diego Herrera', '1998-06-28', 'contraseña13', 'diego@example.com', 13),
    ('Carmen Silva', '1986-12-10', 'contraseña14', 'carmen@example.com', 14),
    ('Javier Jiménez', '1994-03-24', 'contraseña15', 'javier@example.com', 15);

-- Insertar datos adicionales en la tabla Apoya
INSERT INTO Apoya (id_usuario, id_campana) VALUES
    (11, 11),
    (12, 12),
    (13, 13),
    (14, 14),
    (15, 15);

-- Insertar datos adicionales en la tabla libreria
INSERT INTO libreria (nombre, id_usuario) VALUES
    ('Colección Premium 11', 11),
    ('Arte en Casa 12', 12),
    ('Libros Digitales 13', 13),
    ('Diseños Exclusivos 14', 14),
    ('Modelos Creativos 15', 15);

-- Insertar datos adicionales en la tabla modelo con fechas desde 2015
INSERT INTO modelo (descripcion, precio, titulo, model, fecha_publicacion, id_usuario, id_libreria) VALUES
    ('Escultura Abstracta', 70, 'Obra de Arte Abstracta', 'abstracto3.stl', '2015-08-15', 11, 11),
    ('Pintura en Óleo', 130, 'Retrato Clásico', 'retrato2.jpg', '2016-06-20', 12, 12),
    ('Diseño de Producto Elegante', 95, 'Lámpara de Lujo', 'lamp3.obj', '2017-04-25', 13, 13),
    ('Escultura en Bronce', 60, 'Figura Esculpida', 'escultura.obj', '2018-10-30', 14, 14),
    ('Arte Digital', 85, 'Arte Digital Colorido', 'arte.jpg', '2019-12-15', 15, 15);

-- Insertar datos adicionales en la tabla etiqueta
INSERT INTO etiqueta (nombre, id_modelo) VALUES
    ('Creatividad', 11),
    ('Retrato', 12),
    ('Diseño de Lujo', 13),
    ('Escultura', 14),
    ('Arte Digital', 15);

-- Insertar datos adicionales en la tabla contiene
INSERT INTO contiene (id_modelo, id_Tier) VALUES
    (11, 11),
    (12, 12),
    (13, 13),
    (14, 14),
    (15, 15);

-- Insertar datos adicionales en la tabla carro_compra con fechas desde 2015
INSERT INTO carro_compra (id_usuario, total, fecha_pago, descripcion) VALUES
    (11, 110.0, '2015-10-12', 'Compra de arte'),
    (12, 160.0, '2016-07-20', 'Colección de modelos 3D'),
    (13, 220.0, '2017-03-25', 'Esculturas únicas'),
    (14, 280.0, '2018-11-30', 'Diseños personalizados'),
    (15, 320.0, '2019-10-15', 'Arte contemporáneo');


-- Insertar datos adicionales en la tabla tribu
INSERT INTO tribu (descripcion) VALUES
    ('Tribu F'),
    ('Tribu G'),
    ('Tribu H'),
    ('Tribu I'),
    ('Tribu J');

-- Insertar datos adicionales en la tabla Categoria
INSERT INTO Categoria (id_categoria, nombre, id_tribu) VALUES
    (16, 'Categoría 16', 16),
    (17, 'Categoría 17', 17),
    (18, 'Categoría 18', 18),
    (19, 'Categoría 19', 19),
    (20, 'Categoría 20', 20);

-- Insertar datos adicionales en la tabla tier
INSERT INTO tier (nivelApoyo, recompensa) VALUES
    (16, 'Agradecimiento en redes sociales'),
    (17, 'Invitación a evento exclusivo'),
    (18, 'Acceso a contenido premium'),
    (19, 'Producto personalizado de edición limitada'),
    (20, 'Reconocimiento en la comunidad');

-- Insertar datos adicionales en la tabla campana
INSERT INTO campana (descricipcion, pioneros, comentarios, dinerorecaudado) VALUES
    ('Campaña 16: Innovación Tecnológica 2', 550, 'Juntos somos imparables', 1650.0),
    ('Campaña 17: Arte y Cultura 2', 350, 'Celebrando la creatividad', 1050.0),
    ('Campaña 18: Educación de Calidad 2', 750, 'Apoyemos la educación', 2250.0),
    ('Campaña 19: Diseño Sostenible 2', 450, 'Por un mundo más verde', 1350.0),
    ('Campaña 20: Comunidad Creativa 2', 900, 'Unidos en la diversidad', 2700.0);

-- Insertar datos adicionales en la tabla usuario
INSERT INTO usuario (nombre, fecha_nacimi, contrasena, correo, id_tribu) VALUES
    ('Elena Martínez', '1992-04-12', 'contraseña16', 'elena@example.com', 16),
    ('Pedro García', '1996-08-25', 'contraseña17', 'pedro@example.com', 17),
    ('Laura Rodríguez', '1989-11-30', 'contraseña18', 'laura@example.com', 18),
    ('Miguel Fernández', '1997-07-05', 'contraseña19', 'miguel@example.com', 19),
    ('Isabel Pérez', '1993-03-18', 'contraseña20', 'isabel@example.com', 20);

-- Insertar datos adicionales en la tabla Apoya
INSERT INTO Apoya (id_usuario, id_campana) VALUES
    (16, 16),
    (17, 17),
    (18, 18),
    (19, 19),
    (20, 20);

-- Insertar datos adicionales en la tabla libreria
INSERT INTO libreria (nombre, id_usuario) VALUES
    ('Colección de Éxito 16', 16),
    ('Arte en Casa 17', 17),
    ('Biblioteca Digital 18', 18),
    ('Diseños Exclusivos 19', 19),
    ('Modelos Creativos 20', 20);

-- Insertar datos adicionales en la tabla modelo con fechas desde 2015
INSERT INTO modelo (descripcion, precio, titulo, model, fecha_publicacion, id_usuario, id_libreria) VALUES
    ('Escultura Contemporánea', 75, 'Obra de Arte Contemporáneo', 'contemporaneo.stl', '2015-12-20', 16, 16),
    ('Pintura al Óleo', 140, 'Retrato Moderno', 'retrato3.jpg', '2016-11-15', 17, 17),
    ('Diseño Elegante', 100, 'Lámpara de Diseño', 'lamp4.obj', '2017-09-10', 18, 18),
    ('Escultura en Mármol', 70, 'Figura en Mármol', 'marmol.obj', '2018-08-05', 19, 19),
    ('Arte Digital Abstracto', 90, 'Arte Abstracto en Digital', 'abstracto4.jpg', '2019-07-30', 20, 20);

-- Insertar datos adicionales en la tabla etiqueta
INSERT INTO etiqueta (nombre, id_modelo) VALUES
    ('Contemporáneo', 16),
    ('Pintura', 17),
    ('Diseño de Lámpara', 18),
    ('Mármol', 19),
    ('Arte Digital', 20);

-- Insertar datos adicionales en la tabla contiene
INSERT INTO contiene (id_modelo, id_Tier) VALUES
    (16, 16),
    (17, 17),
    (18, 18),
    (19, 19),
    (20, 20);

-- Insertar datos adicionales en la tabla carro_compra con fechas desde 2015
INSERT INTO carro_compra (id_usuario, total, fecha_pago, descripcion) VALUES
    (16, 120.0, '2015-06-10', 'Compra de arte contemporáneo'),
    (17, 170.0, '2016-04-15', 'Colección de pinturas'),
    (18, 240.0, '2017-02-20', 'Diseños exclusivos de lámparas'),
    (19, 290.0, '2018-10-25', 'Esculturas en mármol personalizadas'),
    (20, 330.0, '2019-09-30', 'Arte digital abstracto');
    