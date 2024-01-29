use yourminifactory;

create table administrador(
	id_admin int not null primary key auto_increment,
    nombre varchar(100) not null,
    fecha_nacimi date not null,
    contrasena varchar(100) not null,
    correo varchar(100) not null,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

// Trigger 
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






