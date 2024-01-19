use yourminifactory;
Alter table modelo
add visibilidad boolean;
Update modelo 
set visibilidad = True
where id_modelo>= 0;


-- Quiero que casa usuario tenga por defecto 'Mi Librería' 
UPDATE libreria 
SET nombre = 'Mi Librería' 
WHERE id_libreria >0 ; 

