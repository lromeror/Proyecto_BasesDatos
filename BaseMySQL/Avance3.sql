use yourminifactory;

-- Usuarios
CREATE USER 'usuario1' IDENTIFIED BY 'contraseña1';
CREATE USER 'usuario2' IDENTIFIED BY 'contraseña2';
CREATE USER 'usuario3' IDENTIFIED BY 'contraseña3';
CREATE USER 'usuario4' IDENTIFIED BY 'contraseña4';
CREATE USER 'usuario5' IDENTIFIED BY 'contraseña5';

-- Permisos 
-- Usuario 1: Insertar y Eliminar 
GRANT INSERT, DELETE ON yourminifactory.* TO 'usuario1';

-- Usuario 2: Insertas y Update
GRANT INSERT,UPDATE ON yourminifactory.* TO 'usuario2';

-- Usuario 3: Todos los permisos
GRANT ALL PRIVILEGES ON yourminifactory.* TO 'usuario3';

-- Usuario 4: T Todos los permisos y que pueda dar permiso
GRANT ALL PRIVILEGES ON yourminifactory.* TO 'usuario4' WITH GRANT OPTION;

-- Usuario 5: Select y Update a una tabla en específico. 
GRANT SELECT, UPDATE ON yourminifactory.usuario  TO 'usuario5';

-- Permiso a Stored Procedure 
GRANT EXECUTE ON PROCEDURE EliminarRegistroAnadir TO 'usuario1';
GRANT EXECUTE ON PROCEDURE InsertarDatoApoya TO 'usuario1';
GRANT EXECUTE ON PROCEDURE InsertarDatoCampana TO 'usuario3';
GRANT EXECUTE ON PROCEDURE InsertarDatoTribu TO 'usuario3';
-- Permiso a Vistas 
GRANT SELECT ON reportecomprausuarios TO 'usuario1';
GRANT SELECT ON reportedecampaniamodelos TO 'usuario1';
GRANT SELECT ON reportecomprausuarios TO 'usuario2';
GRANT SELECT ON reportedecampaniamodelos TO 'usuario2';
GRANT SELECT ON reportecomprausuarios TO 'usuario3';
GRANT SELECT ON reporteusuariotribucampania TO 'usuario3';
GRANT SELECT ON reportecomprausuarios TO 'usuario4';
GRANT SELECT ON reporteusuariotribucampania TO 'usuario4';
GRANT SELECT ON reportecomprausuarios TO 'usuario5';
GRANT SELECT ON reportedecampaniamodelos TO 'usuario5';





