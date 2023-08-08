use naruto;
select * from ninjas 
where 
exists(select * from skill_atributo where nombre_ninja = nombre and accion ="increase" and afecta<>"self" and nombre_atributo="cc resistance" and tipo="TALENT");

select * from ninjas 
where 
exists(select * from skill_atributo where nombre_ninja = nombre and accion ="increase" and afecta<>"self" and nombre_atributo="cc resistance" and tipo="TALENT");

select * from ninjas as n
where 
exists(select * from ninja_awakening_atributo as na where na.ninja = n.nombre and na.accion ="increase" and na.afecta<>"self" and na.nombre_atributo="cc resistance" and na.tipo="TALENT");

select * from ninjas as n
where 
exists(select * from skill_atributo where nombre_ninja = nombre and accion ="increase" and afecta="ally earth chakra nature" and nombre_atributo="cc resistance" and tipo="TALENT")

exists (select * from ninja_awakening_atributo as na where na.ninja = n.nombre and na.accion ="increase" and na.afecta="ally earth chakra nature" and na.nombre_atributo="cc resistance" and na.tipo="TALENT")
or exists(select * from skill_atributo where nombre_ninja = nombre and accion ="increase" and afecta="ally earth chakra nature" and nombre_atributo="cc resistance" and tipo="TALENT")

or exists(select * from ninja_awakening_atributo as na where na.ninja = n.nombre and na.accion ="increase" and na.afecta="all allies" and na.nombre_atributo="cc resistance" and na.tipo="TALENT")
or exists(select * from ninja_awakening_atributo as na where na.ninja = n.nombre and na.accion ="increase" and na.afecta<>'self' and na.nombre_atributo="cc resistance" and na.tipo="TALENT")
or (exists(select * from skill_atributo where nombre_ninja = nombre and accion ="increase" and afecta="ally earth chakra nature" and nombre_atributo="cc resistance" and tipo="TALENT")
or exists(select * from skill_atributo where nombre_ninja = nombre and accion ="increase" and afecta="all allies" and nombre_atributo="cc resistance" and tipo="TALENT")
or exists(select * from skill_atributo where nombre_ninja = nombre and accion ="increase" and afecta<>'self' and nombre_atributo="cc resistance" and tipo="TALENT"));

select * from skill_atributo where nombre_ninja='Hidan' and tipo ='TALENT';
SELECT *
FROM ninjas AS n
WHERE EXISTS (
  SELECT *
  FROM ninja_awakening_atributo AS na
  WHERE na.ninja = n.nombre
    AND (
      (na.accion = "increase" AND na.afecta = "ally earth chakra nature" AND na.nombre_atributo = "cc resistance" AND na.tipo = "TALENT")
      OR (na.accion = "increase" AND na.afecta = "enemy fire chakra nature" AND na.nombre_atributo = "attack power" AND na.tipo = "SKILL")
      -- Agrega más condiciones aquí si deseas buscar más atributos
    )
)
AND EXISTS (
  SELECT *
  FROM skill_atributo AS sa
  WHERE sa.nombre_ninja = n.nombre
    AND (
      (sa.accion = "increase" AND sa.afecta = "ally earth chakra nature" AND sa.nombre_atributo = "cc resistance" AND sa.tipo = "TALENT")
      OR (sa.accion = "increase" AND sa.afecta = "enemy fire chakra nature" AND sa.nombre_atributo = "attack power" AND sa.tipo = "SKILL")
      -- Agrega más condiciones aquí si deseas buscar más atributos
    )
);

SELECT * 
FROM ninjas AS n 
WHERE 
(
  EXISTS (
    SELECT 1 
    FROM skill_atributo AS sa 
    WHERE sa.nombre_ninja = n.nombre 
      AND sa.accion = "increase" 
      AND sa.afecta = "ally earth chakra nature" 
      AND sa.nombre_atributo = "cc resistance" 
      AND sa.tipo = "TALENT"
  ) 
  OR EXISTS (
    SELECT 1 
    FROM ninja_awakening_atributo AS na 
    WHERE na.ninja = n.nombre 
      AND na.accion = "increase" 
      AND na.afecta = "ally earth chakra nature" 
      AND na.nombre_atributo = "cc resistance" 
      AND na.tipo = "TALENT"
  )
) 
AND (
  EXISTS (
    SELECT 1 
    FROM skill_atributo AS sa 
    WHERE sa.nombre_ninja = n.nombre 
      AND sa.accion = "increase" 
      AND sa.afecta = "all allies" 
      AND sa.nombre_atributo = "attack" 
      AND sa.tipo = "TALENT"
  ) 
  OR EXISTS (
    SELECT 1 
    FROM ninja_awakening_atributo AS na 
    WHERE na.ninja = n.nombre 
      AND na.accion = "increase" 
      AND na.afecta = "all allies" 
      AND na.nombre_atributo = "attack" 
      AND na.tipo = "TALENT"
  )
);


select * from ninjas as n
where 
exists(select * from skill_atributo where nombre_ninja = nombre and accion ="increase" and afecta="ally earth chakra nature" and nombre_atributo="cc resistance" and tipo="TALENT")

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