insert into atributos(nombre) values('physical attack');
insert into atributos(nombre) values('strategy attack');
insert into atributos(nombre) values('physical defense');
insert into atributos(nombre) values('strategy defense');
insert into atributos(nombre) values('speed');
insert into atributos(nombre) values('power');
insert into atributos(nombre) values('damage rate');
insert into atributos(nombre) values('agility');
insert into atributos(nombre) values('weaken enemy attack');
insert into atributos(nombre) values('avoid injury rate');
insert into atributos(nombre) values('HP');
insert into atributos(nombre) values('after using skill, recovers himself % HP by');
insert into atributos(nombre) values('force');
insert into atributos(nombre) values('chakra');
insert into atributos(nombre) values('defense');
insert into atributos(nombre) values('after attack % dot enemy for 1 round');

insert into atributos(nombre) values('after releasing skill, give 2 random ally fury shield 2 rounds');
insert into atributos(nombre) values('after releasing skill, incinerate 2 random enemies dot % 2 rounds');
insert into atributos(nombre) values('increase 2 random ally Crit % (break limit)');
insert into atributos(nombre) values('after releasing skill, make 2 random enemy enter pain will (cant add buffs) 2 rounds');
insert into atributos(nombre) values('after releasing skill, have 50% chance to make self enter Shielding state (ignore enemy damage, cant be dispelled) 2 rounds');
insert into atributos(nombre) values('attack');

insert into atributos(nombre) values('after release skill dispel 2 random allies debuff');
insert into atributos(nombre) values('increase the success rate of control skills by');
insert into atributos(nombre) values('after release skill has 80% chance to make 2 random enemies enter frozen for 2 rounds');
insert into atributos(nombre) values('punch rate');

insert into atributos(nombre) values('after being attacked, increase self defense by 100%');
insert into atributos(nombre) values('after being attacked, increase self speed by 20% for 2 rounds');

insert into atributos(nombre) values('weaken enemy defense');
insert into atributos(nombre) values('s.attack(can break the limit)');
insert into atributos(nombre) values('when attacked there is an 80% chance to make enemy male ninjas  enter disable for 2 rounds (cant move, cant disperse)');

insert into  equipos(nombre) values('curse seal set');

insert into partes(nombre,nombre_atributo,nombre_equipo,valor) values(
'curse seal kunai','physical attack','curse seal set',55000);
insert into partes(nombre,nombre_atributo,nombre_equipo,valor) values(
'curse seal shuriken','physical attack','curse seal set',55000);
insert into partes(nombre,nombre_atributo,nombre_equipo,valor) values(
'curse seal scroll','strategy attack','curse seal set',55000);
insert into partes(nombre,nombre_atributo,nombre_equipo,valor) values(
'curse seal headband','physical defense','curse seal set',54000);
insert into partes(nombre,nombre_atributo,nombre_equipo,valor) values(
'curse seal armor','physical defense','curse seal set',54000);
insert into partes(nombre,nombre_atributo,nombre_equipo,valor) values(
'curse seal coat','strategy defense','curse seal set',54000);
insert into partes(nombre,nombre_atributo,nombre_equipo,valor) values(
'curse seal boots','speed','curse seal set',55000);
insert into partes(nombre,nombre_atributo,nombre_equipo,valor) values(
'curse seal belt','power','curse seal set',560000);

insert into bonuses(id,nombre_equipo,nombre) values(2,'curse seal set','2 effect : ');
insert into bonuses(id,nombre_equipo,nombre) values(4,'curse seal set','4 effect : ');
insert into bonuses(id,nombre_equipo,nombre) values(6,'curse seal set','6 effect : ');

insert into bonus_atributo(id_bonus,nombre_equipo,nombre_atributo,valor) values(2,'curse seal set','damage rate',30);
insert into bonus_atributo(id_bonus,nombre_equipo,nombre_atributo,valor) values(2,'curse seal set','agility',22000);
insert into bonus_atributo(id_bonus,nombre_equipo,nombre_atributo,valor) values(4,'curse seal set','damage rate',30);
insert into bonus_atributo(id_bonus,nombre_equipo,nombre_atributo,valor) values(4,'curse seal set','weaken enemy attack',35);
insert into bonus_atributo(id_bonus,nombre_equipo,nombre_atributo,valor) values(4,'curse seal set','agility',28000);
insert into bonus_atributo(id_bonus,nombre_equipo,nombre_atributo,valor) values(6,'curse seal set','avoid injury rate',30);
insert into bonus_atributo(id_bonus,nombre_equipo,nombre_atributo,valor) values(6,'curse seal set','HP',20);
insert into bonus_atributo(id_bonus,nombre_equipo,nombre_atributo,valor) values(6,'curse seal set',
'after using skill, recovers himself % HP by',40);



insert into  equipos(nombre) values('sakura set');

insert into partes(nombre,nombre_atributo,nombre_equipo,valor) values(
'sakura kunai','physical attack','sakura set',60000);
insert into partes(nombre,nombre_atributo,nombre_equipo,valor) values(
'sakura shuriken','physical attack','sakura set',60000);
insert into partes(nombre,nombre_atributo,nombre_equipo,valor) values(
'sakura scroll','strategy attack','sakura set',60000);
insert into partes(nombre,nombre_atributo,nombre_equipo,valor) values(
'sakura headband','physical defense','sakura set',60000);
insert into partes(nombre,nombre_atributo,nombre_equipo,valor) values(
'sakura armor','physical defense','sakura set',60000);
insert into partes(nombre,nombre_atributo,nombre_equipo,valor) values(
'sakura coat','strategy defense','sakura set',60000);
insert into partes(nombre,nombre_atributo,nombre_equipo,valor) values(
'sakura boots','speed','sakura set',60000);
insert into partes(nombre,nombre_atributo,nombre_equipo,valor) values(
'sakura belt','power','sakura set',600000);

/*insert into bonuses(id,nombre_equipo,nombre) values(2,'sakura set','2 effect : ');
/*insert into bonuses(id,nombre_equipo,nombre) values(4,'sakura set','4 effect : ');
insert into bonuses(id,nombre_equipo,nombre) values(6,'sakura set','6 effect : ');
/*
insert into bonus_atributo(id_bonus,nombre_equipo,nombre_atributo,valor) values(2,'sakura set','damage rate',40);
insert into bonus_atributo(id_bonus,nombre_equipo,nombre_atributo,valor) values(2,'sakura set','agility',22000);
insert into bonus_atributo(id_bonus,nombre_equipo,nombre_atributo,valor) values(2,'sakura set','after releasing skill, give 2 random ally fury shield 2 rounds',0);
insert into bonus_atributo(id_bonus,nombre_equipo,nombre_atributo,valor) values(4,'sakura set','attack',20);
insert into bonus_atributo(id_bonus,nombre_equipo,nombre_atributo,valor) values(4,'sakura set','after releasing skill, incinerate 2 random enemies dot % 2 rounds'
,120);
insert into bonus_atributo(id_bonus,nombre_equipo,nombre_atributo,valor) values(4,'sakura set','increase 2 random ally Crit % (break limit)',40);

insert into bonus_atributo(id_bonus,nombre_equipo,nombre_atributo,valor) values(6,'sakura set','agility',30000);
insert into bonus_atributo(id_bonus,nombre_equipo,nombre_atributo,valor) values(6,'sakura set','weaken enemy attack',25);
insert into bonus_atributo(id_bonus,nombre_equipo,nombre_atributo,valor) values(6,'sakura set',
'after releasing skill, make 2 random enemy enter pain will (cant add buffs) 2 rounds',0);
insert into bonus_atributo(id_bonus,nombre_equipo,nombre_atributo,valor) values(6,'sakura set',
'after releasing skill, have 50% chance to make self enter Shielding state (ignore enemy damage, cant be dispelled) 2 rounds',0);
*/