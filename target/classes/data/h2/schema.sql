DROP TABLE IF EXISTS atributos;
CREATE TABLE atributos (
  nombre VARCHAR(255) NOT NULL PRIMARY KEY
);
insert into atributos(nombre) values('health by');

DROP TABLE IF EXISTS users;
CREATE TABLE users (
  username VARCHAR(255) NOT NULL,
  enabled BOOLEAN DEFAULT NULL,
  mail VARCHAR(255) DEFAULT NULL,
  password VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (username)
);

DROP TABLE IF EXISTS authorities;
CREATE TABLE authorities (
  authority VARCHAR(255) NOT NULL PRIMARY KEY
);

DROP TABLE IF EXISTS users_roles;
CREATE TABLE users_roles (
  usuario_username VARCHAR(255) NOT NULL,
  authority_role VARCHAR(255) NOT NULL,
  PRIMARY KEY (usuario_username, authority_role),
  FOREIGN KEY (usuario_username) REFERENCES users (username),
  FOREIGN KEY (authority_role) REFERENCES authorities (authority)
);

DROP TABLE IF EXISTS equipos;
CREATE TABLE equipos (
  nombre VARCHAR(255) PRIMARY KEY
);

DROP TABLE IF EXISTS bonuses;
CREATE TABLE bonuses (
  id INTEGER NOT NULL,
  nombre_equipo VARCHAR(255) NOT NULL,
  nombre VARCHAR(255) NOT NULL,
  PRIMARY KEY (id,nombre_equipo),
  FOREIGN KEY (nombre_equipo) REFERENCES equipos (nombre)
);

DROP TABLE IF EXISTS bonus_atributo;
CREATE TABLE bonus_atributo (
  id_bonus INTEGER NOT NULL,
  nombre_equipo VARCHAR(255) NOT NULL,
  nombre_atributo VARCHAR(255) NOT NULL,
  valor INTEGER NOT NULL,
  accion VARCHAR(255) DEFAULT NULL,
  condicion VARCHAR(255) DEFAULT NULL,
  afecta VARCHAR(255) DEFAULT NULL,
  tiempo VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id_bonus,nombre_equipo,nombre_atributo),
  FOREIGN KEY (nombre_equipo) REFERENCES equipos (nombre),
  FOREIGN KEY (nombre_atributo) REFERENCES atributos (nombre),
  FOREIGN KEY (id_bonus, nombre_equipo) REFERENCES bonuses (id, nombre_equipo)
);

-- Y así sucesivamente para todas las demás tablas.


DROP TABLE IF EXISTS partes;
CREATE TABLE partes (
  nombre VARCHAR(255) PRIMARY KEY,
  nombre_atributo VARCHAR(255) NOT NULL,
  nombre_equipo VARCHAR(255) NOT NULL,
  valor INTEGER DEFAULT NULL,
  image BLOB,
  FOREIGN KEY (nombre_atributo) REFERENCES atributos (nombre),
  FOREIGN KEY (nombre_equipo) REFERENCES equipos (nombre)
);

DROP TABLE IF EXISTS set_accesorios;
CREATE TABLE set_accesorios (
  nombre VARCHAR(255) PRIMARY KEY
);

DROP TABLE IF EXISTS partes_accesorios;
CREATE TABLE partes_accesorios (
  nombre VARCHAR(255) PRIMARY KEY,
  nombre_set VARCHAR(255) NOT NULL,
  nombre_atributo VARCHAR(255) NOT NULL,
  tipo VARCHAR(255) NOT NULL,
  valor INTEGER DEFAULT NULL,
  image BLOB,
  FOREIGN KEY (nombre_set) REFERENCES set_accesorios (nombre),
  FOREIGN KEY (nombre_atributo) REFERENCES atributos (nombre)
);

DROP TABLE IF EXISTS bonusaccesorios;
CREATE TABLE bonusaccesorios (
  tipo VARCHAR(255) NOT NULL,
  nombre_set_accesorios VARCHAR(255) NOT NULL,
  PRIMARY KEY (tipo,nombre_set_accesorios),
  FOREIGN KEY (nombre_set_accesorios) REFERENCES set_accesorios (nombre)
);

DROP TABLE IF EXISTS bonusaccesorios_atributo;
CREATE TABLE bonusaccesorios_atributo (
  tipo_bonus VARCHAR(255) NOT NULL,
  nombre_set_accesorios VARCHAR(255) NOT NULL,
  nombre_atributo VARCHAR(255) NOT NULL,
  valor INTEGER NOT NULL,
  accion VARCHAR(255) DEFAULT NULL,
  condicion VARCHAR(255) DEFAULT NULL,
  afecta VARCHAR(255) DEFAULT NULL,
  tiempo VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (tipo_bonus,nombre_atributo,nombre_set_accesorios),
  FOREIGN KEY (nombre_set_accesorios) REFERENCES set_accesorios (nombre),
  FOREIGN KEY (nombre_atributo) REFERENCES atributos (nombre),
  FOREIGN KEY (nombre_set_accesorios, tipo_bonus) REFERENCES bonusaccesorios (nombre_set_accesorios, tipo)
);


DROP TABLE IF EXISTS ninjas;
CREATE TABLE ninjas (
  nombre VARCHAR(255) PRIMARY KEY NOT NULL,
  chakra_nature VARCHAR(255),
  tipo VARCHAR(255),
  formacion VARCHAR(255),
  sexo VARCHAR(255),
  ninja_image BLOB,
  ninja_stat_image BLOB
);

DROP TABLE IF EXISTS ninja_stats;
CREATE TABLE ninja_stats (
  ninja VARCHAR(255) NOT NULL,
  nivel VARCHAR(255) NOT NULL,
  PRIMARY KEY (ninja,nivel),
  FOREIGN KEY (ninja) REFERENCES ninjas (nombre)
);

DROP TABLE IF EXISTS ninja_skills;
CREATE TABLE ninja_skills (
  nombre VARCHAR(255) NOT NULL,
  ninja VARCHAR(255) NOT NULL,
  tipo VARCHAR(255) NOT NULL,
  texto VARCHAR(2000),
  PRIMARY KEY (nombre,tipo,ninja),
  FOREIGN KEY (ninja) REFERENCES ninjas (nombre)
);

DROP TABLE IF EXISTS ninja_awakening;
CREATE TABLE ninja_awakening (
  nombre VARCHAR(255) NOT NULL,
  ninja VARCHAR(255),
  nivel VARCHAR(255) NOT NULL,
  activo INTEGER,
  tipo VARCHAR(255) NOT NULL,
  texto VARCHAR(2000),
  PRIMARY KEY (nombre,tipo,nivel),
  FOREIGN KEY (ninja) REFERENCES ninjas (nombre)
);

DROP TABLE IF EXISTS stat_atributo;
CREATE TABLE stat_atributo (
  ninja VARCHAR(255) NOT NULL,
  nivel VARCHAR(255) NOT NULL,
  nombre_atributo VARCHAR(255) NOT NULL,
  valor INTEGER,
  PRIMARY KEY (ninja,nombre_atributo,nivel),
  FOREIGN KEY (ninja, nivel) REFERENCES ninja_stats (ninja, nivel),
  FOREIGN KEY (nombre_atributo) REFERENCES atributos (nombre)
);

DROP TABLE IF EXISTS skill_atributo;
CREATE TABLE skill_atributo (
  nombre_skill VARCHAR(255) NOT NULL,
  nombre_ninja VARCHAR(255) NOT NULL,
  tipo VARCHAR(255) NOT NULL,
  nombre_atributo VARCHAR(255) NOT NULL,
  valor INTEGER,
  accion VARCHAR(255) NOT NULL,
  afecta VARCHAR(255) NOT NULL,
  condicion VARCHAR(255),
  tiempo VARCHAR(255),
  PRIMARY KEY (nombre_ninja,nombre_atributo,nombre_skill,tipo,accion,afecta),
  FOREIGN KEY (nombre_ninja, nombre_skill, tipo) REFERENCES ninja_skills (ninja, nombre, tipo),
  FOREIGN KEY (nombre_atributo) REFERENCES atributos (nombre)
);

DROP TABLE IF EXISTS ninja_awakening_atributo;
CREATE TABLE ninja_awakening_atributo (
  nombre VARCHAR(255) NOT NULL,
  ninja VARCHAR(255) NOT NULL,
  nivel VARCHAR(255) NOT NULL,
  nombre_atributo VARCHAR(255) NOT NULL,
  valor INTEGER,
  tipo VARCHAR(255) NOT NULL,
  accion VARCHAR(255) NOT NULL,
  afecta VARCHAR(255) NOT NULL,
  condicion VARCHAR(255),
  tiempo VARCHAR(255),
  PRIMARY KEY (nivel,ninja,tipo,nombre_atributo,nombre,accion,afecta),
  FOREIGN KEY (nombre, tipo, nivel) REFERENCES ninja_awakening (nombre, tipo, nivel),
  FOREIGN KEY (nombre_atributo) REFERENCES atributos (nombre)
);

DROP TABLE IF EXISTS set_accesorios_user;
CREATE TABLE set_accesorios_user (
  id INT PRIMARY KEY,
  nombre VARCHAR(255) DEFAULT NULL,
  username VARCHAR(255) DEFAULT NULL
);

DROP TABLE IF EXISTS set_accesorios_user_partes;
CREATE TABLE set_accesorios_user_partes (
  id_set_accesorio INT NOT NULL,
  nombre_parte VARCHAR(255) NOT NULL,
  FOREIGN KEY (nombre_parte) REFERENCES partes_accesorios(nombre),
  FOREIGN KEY (id_set_accesorio) REFERENCES set_accesorios_user(id)
);

DROP TABLE IF EXISTS set_accesorios_user_bonus;
CREATE TABLE set_accesorios_user_bonus (
  id INT NOT NULL,
  nombre_set_accesorio_original VARCHAR(255) NOT NULL,
  tipo VARCHAR(255) NOT NULL,
  FOREIGN KEY (nombre_set_accesorio_original, tipo) REFERENCES bonusaccesorios(nombre_set_accesorios, tipo),
  FOREIGN KEY (id) REFERENCES set_accesorios_user(id)
);

DROP TABLE IF EXISTS equipos_user;
CREATE TABLE equipos_user (
  id INT PRIMARY KEY,
  nombre VARCHAR(255) DEFAULT NULL,
  username VARCHAR(255) DEFAULT NULL
);

DROP TABLE IF EXISTS equipos_user_partes;
CREATE TABLE equipos_user_partes (
  id INT NOT NULL,
  nombre_parte VARCHAR(255) NOT NULL,
  FOREIGN KEY (nombre_parte) REFERENCES partes(nombre),
  FOREIGN KEY (id) REFERENCES equipos_user(id)
);

DROP TABLE IF EXISTS equipos_user_bonus;
CREATE TABLE equipos_user_bonus (
  id INT NOT NULL,
  nombre_equipo_original VARCHAR(255) NOT NULL,
  id_bonus INT NOT NULL,
  FOREIGN KEY (nombre_equipo_original, id_bonus) REFERENCES bonuses(nombre_equipo, id),
  FOREIGN KEY (id) REFERENCES equipos_user(id)
);


DROP TABLE IF EXISTS ninja_user_formation;
CREATE TABLE ninja_user_formation (
  id INT PRIMARY KEY,
  chakra_nature VARCHAR(255) DEFAULT NULL,
  formation VARCHAR(255) DEFAULT NULL,
  nombre VARCHAR(255) DEFAULT NULL,
  sex VARCHAR(255) DEFAULT NULL,
  skill VARCHAR(255) DEFAULT NULL,
  username VARCHAR(255) DEFAULT NULL,
  id_user_set_accesorios INT DEFAULT NULL,
  id_user_set INT DEFAULT NULL,
  nombre_ninja VARCHAR(255) DEFAULT NULL,
  FOREIGN KEY (id_user_set_accesorios) REFERENCES set_accesorios_user(id),
  FOREIGN KEY (id_user_set) REFERENCES equipos_user(id),
  FOREIGN KEY (nombre_ninja) REFERENCES ninjas(nombre)
);

DROP TABLE IF EXISTS formation_user;
CREATE TABLE formation_user (
  id INT PRIMARY KEY,
  nombre VARCHAR(255) DEFAULT NULL,
  username VARCHAR(255) DEFAULT NULL
);

DROP TABLE IF EXISTS formation_user_ninja_user;
CREATE TABLE formation_user_ninja_user (
  formation_user_id INT NOT NULL,
  ninja_user_id INT NOT NULL,
  FOREIGN KEY (ninja_user_id) REFERENCES ninja_user_formation(id),
  FOREIGN KEY (formation_user_id) REFERENCES formation_user(id)
);


DROP TABLE IF EXISTS hibernate_sequence;
CREATE TABLE hibernate_sequence (
  next_val INT DEFAULT NULL
);

INSERT INTO hibernate_sequence VALUES (29);

