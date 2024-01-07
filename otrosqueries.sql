
use yourminifactory;
Alter table modelo
add visibilidad boolean;
Update modelo 
set visibilidad = True
where id_modelo>= 0;