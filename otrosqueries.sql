
use yourminifactory;
Alter table modelo
add visibilidad boolean;
Update modelo 
set visibilidad = Trueq
where id_modelo>= 0;