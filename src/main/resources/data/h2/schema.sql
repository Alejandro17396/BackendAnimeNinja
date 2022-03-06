
create table equipos(

    nombre 			varchar(200)			,

	primary key(nombre)
);

create table  atributos(

    nombre			varchar(200)			,

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
        
       
        foreign key(nombre_equipo)	references equipos(nombre),
        primary key(id,nombre_equipo)
         

);


create table bonus_atributo(

	id_bonus			bigint			not null,
    nombre_equipo		varchar(200)	not null,
    nombre_atributo		varchar(200)	not null,
    valor				bigint			not null,
    
    primary key(id_bonus,nombre_equipo,nombre_atributo),
    foreign key(nombre_equipo) references equipos(nombre),
    foreign key(id_bonus) references bonuses(id),
    foreign key(nombre_atributo) references atributos(nombre)

);
create table set_accesorios(

	nombre		varchar(200)					not null,
    
    primary key(nombre)

);

create table partes_accesorios(
	
    nombre				varchar(200)			not null,
    nombre_set			varchar(200)			not null,
    nombre_atributo		varchar(200)			not null,
    valor				bigint							,
    
    primary key(nombre),
    foreign key(nombre_set)			references set_accesorios(nombre),
    foreign key(nombre_atributo)	references	atributos(nombre)
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
    foreign key(tipo_bonus) references bonusaccesorios(tipo),
    foreign key(nombre_atributo)  references atributos(nombre)
);