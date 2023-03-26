package com.alejandro.animeninja.integration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.ClaveBonus;

public interface BonusRepository  extends JpaRepository<Bonus, ClaveBonus>,
				BonusCustomRepository, JpaSpecificationExecutor<Bonus>  {
	
	/*@Query(value = "select distinct bonuses.nombre_equipo from bonuses left join equipos\r\n"
			+ "on equipos.nombre=bonuses.nombre_equipo left join partes \r\n"
			+ "on equipos.nombre=partes.nombre_equipo\r\n"
			+ "where partes.nombre like '%kunai%' and partes.valor>55000;", nativeQuery = true)*/
	@Query(value = "select * from bonuses join equipos on equipos.nombre=bonuses.nombre_equipo left join partes on equipos.nombre=partes.nombre_equipo where partes.nombre like '%kunai%' and partes.valor>=:valor",
			nativeQuery = true)
	List <Bonus> findBySets(@Param("valor") Long valor);
	
	List <Bonus> findByEquipo(String name);
	
	Bonus findByIdAndEquipo(@Param("id") Long id,@Param("equipo") String equipo);
	//List<Bonus> findBySets(@Param("parte") String parte,@Param("valor") Long valor);
	

}
