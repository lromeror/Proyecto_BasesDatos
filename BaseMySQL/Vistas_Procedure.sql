use yourminifactory;

-- Vistas

CREATE VIEW reportecomprausuarios AS
    SELECT 
        u.nombre AS UserName, cc.total AS TotalSpent,cc.fecha_pago AS PaymentDate,l.nombre AS ItemName, m.descripcion AS ItemDescription
    FROM
        usuario u JOIN carro_compra cc ON u.id_usuario = cc.id_usuario JOIN anadir a ON cc.id_carrito = a.id_carrito 
        JOIN modelo m ON a.id_modelo = m.id_modelo JOIN libreria l ON m.id_libreria = l.id_libreria;
        
CREATE VIEW reportedecampaniamodelos AS
    SELECT  m.descripcion AS ModelDescription,  m.precio AS Price, m.fecha_publicacion AS PublicationDate, m.visibilidad AS Visibility,
        c.id_campana AS campanaId, c.descripcion AS camapanaDesc
    FROM
		asignacion a JOIN modelo m ON a.id_modelo = m.id_modelo JOIN campana c ON a.id_campana = c.id_campana;

        
CREATE VIEW reporteusuariotribucampania AS
SELECT 
    t.name AS TribeName, t.descripcion AS TribeDescription, u.nombre AS UserName, c.descripcion AS CampaignDescription
FROM
    tribu t JOIN tribu_user tu ON t.id_tribu = tu.id_tribu JOIN usuario u ON tu.id_user = u.id_usuario JOIN asignacion a ON u.id_usuario = a.id_tier JOIN campana c ON a.id_campana = c.id_campana;
-- Trigger 
-- Al momento de crear un nuevo usuario, me cree una libreria que todo usuario tiene por default, y su respectivo carro de compra


DELIMITER |
CREATE TRIGGER CreateAfterUser AFTER INSERT ON usuario
FOR EACH ROW
BEGIN
    INSERT INTO libreria (nombre, id_usuario)
    VALUES ('Mi Librería', NEW.id_usuario);
    INSERT INTO carro_compra (id_usuario, total)
    VALUES (NEW.id_usuario,0);
END;

DELIMITER |

CREATE TRIGGER set_fechas_before_insert
BEFORE INSERT ON Apoya
FOR EACH ROW
BEGIN
	SET NEW.fecha_inicio = NOW();
	SET NEW.fecha_limite = DATE_ADD(NOW(),INTERVAL 1 MONTH);
END;
| DELIMITER ;


DELIMITER |
CREATE TRIGGER CreateAfterUser AFTER INSERT ON usuario
FOR EACH ROW
BEGIN
    INSERT INTO libreria (nombre, id_usuario)
    VALUES ('Mi Librería', NEW.id_usuario);
    INSERT INTO carro_compra (id_usuario, total)
    VALUES (NEW.id_usuario,0);
END;
| DELIMITER ; 

DELIMITER |
CREATE TRIGGER CreateUnirseApoyar AFTER INSERT ON Apoya
FOR EACH ROW
BEGIN
    UPDATE campana
    SET pioneros = pioneros + 1 
    WHERE id_campana = NEW.id_campana;
END;
| DELIMITER ; 


DELIMITER |
CREATE TRIGGER CreateSalirseApoyar AFTER DELETE ON Apoya
FOR EACH ROW
BEGIN
    UPDATE campana
    SET pioneros = pioneros - 1 
    WHERE id_campana = OLD.id_campana;
END;
| DELIMITER ; 


-- Stored Procedure
DELIMITER |
CREATE PROCEDURE EliminarRegistroAnadir (IN _idCarrito INT, IN _idModelo INT)
BEGIN
    DELETE FROM anadir WHERE id_carrito = _idCarrito AND id_modelo = _idModelo;
END;
| DELIMITER ;

DELIMITER |
CREATE PROCEDURE EliminarRegistroTribu (IN id_user_ INT, IN id_tribu_ INT)
BEGIN
    DELETE FROM tribu_user WHERE id_user = id_user_ AND id_tribu = id_tribu_;
END;
| DELIMITER ;

DELIMITER |
CREATE PROCEDURE EliminarRegistroApoya(IN _idUsuario INT, IN _idCampana INT)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al eliminar registro en Apoya.';
    END;

    START TRANSACTION;

    DELETE FROM Apoya WHERE id_usuario = _idUsuario AND id_campana = _idCampana;

    COMMIT;
END
| DELIMITER ;

DELIMITER |
CREATE PROCEDURE InsertarDatoApoya(
    IN _idUsuario INT, 
    IN _idCampana INT, 
    IN _nivel INT
)
BEGIN
    DECLARE existente INT;

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar en Apoya.';
    END;

    START TRANSACTION;

    SELECT COUNT(*) INTO existente FROM Apoya WHERE id_usuario = _idUsuario AND id_campana = _idCampana;

    IF existente > 0 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Ya existe un registro con el mismo id_usuario y id_campana en Apoya.';
    ELSE
        INSERT INTO Apoya (id_usuario, id_campana, nivel) VALUES (_idUsuario, _idCampana, _nivel);
        COMMIT;
    END IF;
END
| DELIMITER ;

DELIMITER |
CREATE PROCEDURE InsertarDatoCampana(
    IN _descripcion VARCHAR(255), 
    IN _pioneros INT, 
    IN _dinerorecaudado DECIMAL(10, 2)
)
BEGIN
    DECLARE existente INT;

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar en campana.';
    END;

    START TRANSACTION;

    SELECT COUNT(*) INTO existente FROM campana WHERE descripcion = _descripcion;

    IF existente > 0 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Ya existe una campaña con la misma descripción.';
    ELSE
        INSERT INTO campana (descripcion, pioneros, dinerorecaudado) VALUES (_descripcion, _pioneros, _dinerorecaudado);
        COMMIT;
    END IF;
END;
| DELIMITER ;

DELIMITER |
CREATE PROCEDURE InsertarDatoCarComp(IN id_car INT, IN id_mod INT)
BEGIN
    DECLARE existente INT;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar datos.';
    END;

    START TRANSACTION;

    SELECT COUNT(*)
    INTO existente
    FROM anadir
    WHERE id_carrito = id_car AND id_modelo = id_mod;

    IF existente > 0 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El dato ya existe en la tabla.';
    ELSE
        INSERT INTO anadir (id_carrito, id_modelo) VALUES (id_car, id_mod);
        COMMIT;
    END IF;
END;
| DELIMITER ;




 DELIMITER |
 CREATE PROCEDURE InsertarDatoSuper (IN tableName VARCHAR(64), IN _nombre VARCHAR(255), IN _fecha DATE, IN _contrasena VARCHAR(255), IN _correo VARCHAR(255))
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar datos.';
    END;

    START TRANSACTION;

    SET @s = CONCAT('INSERT INTO ', tableName, ' (nombre, fecha_nacimi, contrasena, correo) VALUES (?, ?, ?, ?)');
    PREPARE stmt FROM @s;
    SET @nombre = _nombre, @fecha = _fecha, @contrasena = _contrasena, @correo = _correo;
    EXECUTE stmt USING @nombre, @fecha, @contrasena, @correo;
    DEALLOCATE PREPARE stmt;

    COMMIT;
END;
| DELIMITER ;


DELIMITER |
CREATE PROCEDURE InsertarDatoTribu (IN _descripcion VARCHAR(255), IN _title VARCHAR(255))
BEGIN
    DECLARE existente INT;

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar en tribu.';
    END;

    START TRANSACTION;

    SELECT COUNT(*) INTO existente FROM tribu WHERE descripcion = _descripcion OR title = _title;

    IF existente > 0 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Ya existe una tribu con la misma descripción o título.';
    ELSE
        INSERT INTO tribu (descripcion, title) VALUES (_descripcion, _title);
        COMMIT;
    END IF;
END;
| DELIMITER ;

DELIMITER |
CREATE PROCEDURE InsertarModelo(
    IN _descripcion VARCHAR(255), 
    IN _precio DECIMAL(10, 2), 
    IN _titulo VARCHAR(255), 
    IN _model VARCHAR(255), 
    IN _id_libreria INT, 
    IN _visibilidad BOOLEAN
)
BEGIN
    DECLARE existente INT;

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar modelo.';
    END;

    START TRANSACTION;

    SELECT COUNT(*) INTO existente FROM modelo WHERE titulo = _titulo AND model = _model;

    IF existente > 0 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Ya existe un modelo con el mismo título y modelo.';
    ELSE
        INSERT INTO modelo (descripcion, precio, titulo, model, fecha_publicacion, id_libreria, visibilidad)
        VALUES (_descripcion, _precio, _titulo, _model, CURDATE(), _id_libreria, _visibilidad);

        COMMIT;
    END IF;
END;
| DELIMITER ;

DELIMITER |
CREATE PROCEDURE InsertarUsuario(IN _nombre VARCHAR(100), IN _fecha_nacimi DATE, IN _contrasena VARCHAR(100), IN _correo VARCHAR(100))
BEGIN
    DECLARE existente INT;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar usuario.';
    END;
    START TRANSACTION;

    SELECT COUNT(*)
    INTO existente
    FROM usuario
    WHERE correo = _correo;
    
    IF existente > 0 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Ya existe un usuario con el mismo correo.';
    ELSE
        INSERT INTO usuario (nombre, fecha_nacimi, contrasena, correo) VALUES (_nombre, _fecha_nacimi, _contrasena, _correo);
        COMMIT;
    END IF;
END;
| DELIMITER ;

DELIMITER |

DELIMITER |
CREATE PROCEDURE InsertarTribuUser(IN _id_user INT, IN _id_tribu INT)
BEGIN
    IF NOT EXISTS (SELECT 1 FROM usuario WHERE id_usuario = _id_user) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El ID de usuario no existe.';
    ELSEIF NOT EXISTS (SELECT 1 FROM tribu WHERE id_tribu = _id_tribu) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El ID de tribu no existe.';
    ELSE
        INSERT INTO tribu_user (id_user, id_tribu) VALUES (_id_user, _id_tribu);
    END IF;
END;
| DELIMITER ;

