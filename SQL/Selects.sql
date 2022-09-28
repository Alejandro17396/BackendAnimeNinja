use naruto;
select * from ninjas 
where 
exists(select * from skill_atributo where nombre_ninja = nombre and accion ="increase" and afecta<>"self" and nombre_atributo="cc resistance" and tipo="TALENT");

select * from ninjas as n
where 
exists(select * from ninja_awakening_atributo as na where na.ninja = n.nombre and na.accion ="increase" and na.afecta<>"self" and na.nombre_atributo="cc resistance" and na.tipo="TALENT")
/*
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
    
);*/