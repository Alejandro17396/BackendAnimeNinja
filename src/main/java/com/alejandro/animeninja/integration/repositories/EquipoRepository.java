package com.alejandro.animeninja.integration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.Equipo;

public interface EquipoRepository extends JpaRepository<Equipo, String>,EquipoCustomRepository {
	
	//@Query("SELECT e FROM Equipo e WHERE EXIST(SELECT b FROM Bonus b WHERE b.equipo=e.nombre EXIST(SELECT ba FROM BonusAtributo WHERE ba.nombreAtributo=:atributo ))")
	
	//@Query("SELECT e FROM Equipo e WHERE EXIST(SELECT b FROM Bonus b WHERE b.equipo=e.nombre)")
	//List <Equipo> buscar(@Param("atributo") String atributo);
	@Query(value ="select distinct equipos.* from equipos join bonuses on equipos.nombre=bonuses.nombre_equipo"
			+ "	join bonus_atributo on bonus_atributo.id_bonus=bonuses.id and bonus_atributo.nombre_equipo=bonuses.nombre_equipo "
			+ "	where bonus_atributo.nombre_atributo=:atributo order by bonuses.nombre_equipo;" , nativeQuery=true)
	List<Equipo> buscar(@Param("atributo") String atributo);
	
	@Query(value ="select equipos.* from equipos join bonuses on equipos.nombre=bonuses.nombre_equipo"
			+ "	join bonus_atributo on bonus_atributo.id_bonus=bonuses.id and bonus_atributo.nombre_equipo=bonuses.nombre_equipo "
			+ "	where bonus_atributo.nombre_atributo in atributos order by bonuses.nombre_equipo;" , nativeQuery=true)
	List<Equipo> buscar2(@Param("atributos") List<String> atributos);
	List <Equipo> findByBonusesContains(Bonus bonus);

}
