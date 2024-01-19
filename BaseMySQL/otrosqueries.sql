
use yourminifactory;
Alter table modelo
add visibilidad boolean;
Update modelo 
set visibilidad = Trueq
where id_modelo>= 0;


-- Quiero que casa usuario tenga por defecto 'Mi Librería' 
-- UPDATE libreria
-- SET nombre = 'Mi Librería' 
-- WHERE id_libreria =1 ; 

-- Modificaciones 
-- Alter table carro_compra
-- 	MODIFY fecha_pago date;
-- Alter table carro_compra
-- 	MODIFY descripcion varchar(255);