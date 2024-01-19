use yourminifactory;
// Trigger 
-- Al momento de crear un nuevo usuario, me cree una libreria que todo usuario tiene por default
DELIMITER |
CREATE TRIGGER CreateUserLibrary AFTER INSERT ON usuario
FOR EACH ROW
BEGIN
    INSERT INTO libreria (nombre, id_usuario)
    VALUES ('Mi Librería', NEW.id_usuario);
END;
| DELIMITER ; 




