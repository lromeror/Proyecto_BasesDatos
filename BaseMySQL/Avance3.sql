use yourminifactory;
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




