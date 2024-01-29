drop database YOURMINIFACTORY;
create database YOURMINIFACTORY;
use YOURMINIFACTORY;

CREATE TABLE Categoria (
    id_categoria int NOT NULL primary key,
    nombre varchar(255)
);
CREATE TABLE tribu(
	id_tribu int not null AUTO_INCREMENT  primary key,
    descripcion varchar(100)  default null,
    name varchar(50) default null,
    num_pioneros int not null,
    id_categoria int not null,
    FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria)
);

create table tier(
	id_tier int not null AUTO_INCREMENT primary key,
    precioMensaul int not null, 
    nivelApoyo int default 0,
    descuento int not null default 0,
    recompensa varchar(100)
);

create table campana(
	id_campana int not null auto_increment primary key,
    descripcion varchar(100) default null,
    pioneros int not null,
    dinerorecaudado double not null,
    link_image varchar(300) default "None"
);


create table usuario(
	id_usuario int not null primary key auto_increment,
    nombre varchar(100) not null,
    fecha_nacimi date not null,
    contrasena varchar(100) not null,
    correo varchar(100) default 'none' not null
);

create table tribu_user(
	id_user int not null,
    id_tribu int not null,
	PRIMARY KEY (id_user, id_tribu),
    FOREIGN KEY (id_user) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_tribu) REFERENCES tribu(id_tribu)
);

CREATE TABLE Apoya (
    id_usuario int NOT NULL,
    id_campana int NOT NULL,
    nivel int NOT NULL ,
    dinero int not null default 0,
    PRIMARY KEY (id_usuario, id_campana),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_campana) REFERENCES campana(id_campana)
);


create table libreria(
	id_libreria int not null primary key auto_increment,
    nombre varchar(100) not null ,
    id_usuario int not null,
    foreign key (id_usuario) references usuario(id_usuario)
);

create table modelo(
	id_modelo int not null auto_increment primary key,
    descripcion varchar(100) not null,
    precio int not null,
    titulo varchar(100) not null ,
    model varchar(100) not null,
    fecha_publicacion date not null ,
    id_libreria int not null,
    foreign key (id_libreria) references libreria(id_libreria)
);

create table carro_compra(
	id_carrito int not null auto_increment primary key,
    id_usuario int not null,
    total double default 0,
    fecha_pago date ,
    descripcion varchar(100),
    state boolean default false,
    foreign key(id_usuario) references usuario(id_usuario)
);

CREATE TABLE anadir (
	id_anadir int NOT NULL auto_increment,
    id_carrito int NOT NULL,
    id_modelo int NOT NULL,
    PRIMARY KEY (id_anadir),
    FOREIGN KEY (id_carrito) REFERENCES carro_compra(id_carrito),
    FOREIGN KEY (id_modelo) REFERENCES modelo(id_modelo)
);

CREATE TABLE Asignacion(
	id_campana int not null,
    id_tier int not null,
    id_modelo int,
    cant int not null default 1,
	PRIMARY KEY (id_campana, id_tier,id_modelo),
	FOREIGN KEY (id_campana) REFERENCES campana(id_campana),
    FOREIGN KEY (id_tier) REFERENCES tier(id_tier),
    FOREIGN KEY (id_modelo) REFERENCES modelo(id_modelo)
);

