package com.alejandro.animeninja.integration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.ClaveBonus;
import com.alejandro.animeninja.bussines.model.BonusAtributo;

public interface BonusRepository  extends JpaRepository<Bonus, ClaveBonus>,BonusCustomRepository  {
	
	@Query(value = "select bonuses.* from bonuses join bonus_atributo "
			+ "on bonus_atributo.id_bonus=bonuses.id and bonus_atributo.nombre_equipo=bonuses.nombre_equipo "
			+ "where bonus_atributo.nombre_atributo='avoid injury rate' order by bonuses.nombre_equipo ;" , nativeQuery=true)
	List <Bonus> buscar();

}
