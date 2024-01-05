create database YOURMINIFACTORY;
use YOURMINIFACTORY;

create table tribu(
	id_tribu int not null AUTO_INCREMENT  primary key,
    descripcion varchar(100)  default null
);

CREATE TABLE Categoria (
    id_categoria int NOT NULL primary key,
    nombre varchar(255),
    id_tribu int NOT NULL,
    -- PRIMARY KEY (id_categoria, id_tribu),
    FOREIGN KEY (id_tribu) REFERENCES Tribu(id_tribu)
);


create table tier(
	id_tier int not null AUTO_INCREMENT primary key,
    nivelApoyo int default 0,
    recompensa varchar(100)
);
create table campana(
	id_campana int not null auto_increment primary key,
    descricipcion varchar(100) default null,
    pioneros int not null,
    comentarios varchar(100) default null,
    dinerorecaudado double not null
);

CREATE TABLE pertenece (
    id_campana int NOT NULL,
    id_Tier int NOT NULL,
    PRIMARY KEY (id_campana, id_Tier),
    FOREIGN KEY (id_campana) REFERENCES campana(id_campana),
    FOREIGN KEY (id_Tier) REFERENCES Tier(id_Tier)
);

create table usuario(
	id_usuario int not null primary key auto_increment,
    nombre varchar(100) not null,
    fecha_impresion date not null,
    contrasena varchar(100) not null,
    correo varchar(100) default 'none' not null,
    id_tribu int not null,
    foreign key (id_tribu) references tribu(id_tribu)
);
CREATE TABLE Apoya (
    id_usuario int NOT NULL,
    id_campana int NOT NULL,
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
    id_usuario int not null,
    id_libreria int not null,
    foreign key (id_usuario) references usuario(id_usuario),
    foreign key (id_libreria) references libreria(id_libreria)
);

create table etiqueta(
	id_etiqueta int not null auto_increment,
    nombre varchar(100) not null,
    id_modelo int not null,
    primary key(id_etiqueta,id_modelo),
    foreign key (id_modelo)references modelo(id_modelo)
);

CREATE TABLE contiene (
    id_modelo int NOT NULL,
    id_Tier int NOT NULL,
    PRIMARY KEY (id_modelo, id_Tier),
    FOREIGN KEY (id_modelo) REFERENCES modelo(id_modelo),
    FOREIGN KEY (id_Tier) REFERENCES tier(id_tier)
);

create table carro_compra(
	id_carrito int not null auto_increment primary key,
    id_usuario int not null,
    total double not null default 0,
    fecha_pago date not null,
    descripcion varchar(100) not null,
    foreign key(id_usuario) references usuario(id_usuario)
);

CREATE TABLE anadir (
    id_carrito int NOT NULL,
    id_modelo int NOT NULL,
    PRIMARY KEY (id_carrito, id_modelo),
    FOREIGN KEY (id_carrito) REFERENCES carro_compra(id_carrito),
    FOREIGN KEY (id_modelo) REFERENCES modelo(id_modelo)
);
