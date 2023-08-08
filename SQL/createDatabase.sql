create database naruto;
use naruto;
use setsnaruto;

drop table partes;
drop table bonus_atributo;
drop table bonuses;
drop table equipos;
drop table partes_accesorios;
drop table bonusaccesorios_atributo;
drop table bonusaccesorios;
drop table set_accesorios;
drop table stat_atributo;
drop table ninja_stats;
drop table skill_atributo;
drop table ninja_skills;
drop table ninja_awakening_atributo;
drop table ninja_awakening;
drop table ninjas;
drop table atributos;



create table  atributos(

    nombre			varchar(200)			,

	primary key(nombre)
);

create table equipos(

    nombre 			varchar(200)			,

	primary key(nombre)
);

create table partes(
		
	nombre				varchar(200)	not null,
    nombre_atributo		varchar(200)	not null,
    nombre_equipo		varchar(200)	not null,
    valor				bigint					,
    primary key (nombre),
    foreign key (nombre_atributo) references atributos(nombre),
    foreign key (nombre_equipo) references equipos(nombre)

);


create table bonuses(
	
		id 					bigint			not null,
        nombre_equipo		varchar(200)	not null,
        nombre				varchar(200)	not null,
        
        primary key(id,nombre_equipo),
        foreign key(nombre_equipo)	references equipos(nombre)

);

create table bonus_atributo(
    id_bonus            bigint          not null,
    nombre_equipo       varchar(200)    not null,
    nombre_atributo     varchar(200)    not null,
    valor               bigint          not null,
    
    primary key(id_bonus,nombre_equipo,nombre_atributo),
    
    foreign key(nombre_equipo) references equipos(nombre),
    foreign key(nombre_atributo) references atributos(nombre),
    
    foreign key(id_bonus, nombre_equipo) references bonuses(id, nombre_equipo)
);

/*create table bonus_atributo(

	id_bonus			bigint			not null,
    nombre_equipo		varchar(200)	not null,
    nombre_atributo		varchar(200)	not null,
    valor				bigint			not null,
    
    primary key(id_bonus,nombre_equipo,nombre_atributo),
    foreign key(nombre_equipo) references equipos(nombre),
    foreign key(id_bonus) references bonuses(id),
    foreign key(nombre_atributo) references atributos(nombre)

);*/
create table set_accesorios(

	nombre		varchar(200)					not null,
    
    primary key(nombre)

);

create table BonusAccesorios(

	tipo						varchar(200)		not null,
    nombre_set_accesorios		varchar(200)		not null,
    primary key (tipo,nombre_set_accesorios),
    foreign key(nombre_set_accesorios)  references  set_accesorios(nombre)
);

create table bonusaccesorios_Atributo(

	tipo_bonus				varchar(200)				not null,
    nombre_set_accesorios	varchar(200)			not null,
    nombre_atributo			varchar(200)				not null,
    valor					bigint						not null,
    
    primary key(tipo_bonus,nombre_atributo,nombre_set_accesorios),
    foreign key(nombre_set_accesorios)	references set_accesorios(nombre),
    foreign key(tipo_bonus,nombre_set_accesorios) references bonusaccesorios(tipo,nombre_set_accesorios),
    foreign key(nombre_atributo)  references atributos(nombre)
);

    foreign key(nombre_set_accesorios)	references set_accesorios(nombre),
    foreign key(tipo_bonus) references bonusaccesorios(tipo),

create table partes_accesorios(
	
    nombre				varchar(200)			not null,
    nombre_set			varchar(200)			not null,
    nombre_atributo		varchar(200)			not null,
    tipo				varchar(200)			not null,
    valor				bigint							,
    
    primary key(nombre),
    foreign key(nombre_set)			references set_accesorios(nombre),
    foreign key(nombre_atributo)	references	atributos(nombre)
);
 


create table  ninjas(

    nombre			varchar(200)			,
    chakra_nature	varchar(40)				,
    tipo			varchar(40)				,
    formacion		varchar(40)				,
	
    primary key(nombre)

);

create table  ninja_stats(

	ninja					varchar(200)							,
    nivel					varchar(200)								,
    primary key(ninja,nivel)										,
    foreign key(ninja)		references ninjas(nombre)
);


create table  stat_atributo(

    ninja							varchar(200)							,
    nivel							varchar(200)								,
    nombre_atributo					varchar(200)							,
	valor							bigint									,
    
    primary key(ninja,nombre_atributo,nivel)				,
    foreign key(ninja,nivel)								references ninja_stats(ninja,nivel)					,
    foreign key(nombre_atributo)							references atributos(nombre)	
);

create table  ninja_skills(

	nombre					varchar(200)							,
    ninja					varchar(200)							,
    tipo					varchar(40)								,
    texto					varchar(2000)							,
	
    primary key(nombre,tipo,ninja)									,
    foreign key(ninja)				references ninjas(nombre)		
);

create table  skill_atributo(

	nombre_skill					varchar(200)							,
    nombre_ninja					varchar(200)							,
    tipo							varchar(40)								,
    nombre_atributo					varchar(200)							,
	valor							bigint									,
    accion							varchar(50)								,
    afecta							varchar(50)								,
    condicion						varchar(50)								,
    tiempo							varchar(50)								,
    
    primary key(nombre_ninja,nombre_atributo,nombre_skill,tipo,accion,afecta)				,
    foreign key(nombre_ninja)								references ninjas(nombre)					,
    foreign key(nombre_skill,tipo)							references ninja_skills(nombre,tipo)		,
    foreign key(nombre_atributo)							references atributos(nombre)
    
);

create table  ninja_awakening(

	nombre							varchar(200)							,
    ninja							varchar(200)							,
    nivel							varchar(20)								,
    activo							boolean									,
    tipo							varchar(40)								,
    texto							varchar(1000)							,
	
    primary key(nombre,tipo,nivel)				,
    foreign key(ninja)			references ninjas(nombre)				
);


create table  ninja_awakening_atributo(

	nombre							varchar(200)							,
    ninja							varchar(200)							,
    nivel							varchar(20)								,
    nombre_atributo					varchar(200)							,
    valor							bigint									,
	tipo							varchar(40)								,
	accion							varchar(50)								,
    afecta							varchar(50)								,
	condicion						varchar(50)								,
    tiempo							varchar(50)								,
    
	primary key(nivel,ninja,tipo,nombre_atributo,nombre,accion,afecta)						,
    foreign key(nombre_atributo)					references atributos(nombre)							,
	foreign key(ninja,nombre,tipo,nivel) 					references ninja_awakening(ninja,nombre,tipo,nivel)					
	
);

SELECT SUM(data_length + index_length) / (1024 * 1024) AS total_size_mb
FROM information_schema.tables
WHERE table_schema = 'naruto';


