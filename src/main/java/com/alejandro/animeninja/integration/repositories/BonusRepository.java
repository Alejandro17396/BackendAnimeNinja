package com.alejandro.animeninja.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.ClaveBonus;

public interface BonusRepository  extends JpaRepository<Bonus, ClaveBonus>,
				BonusCustomRepository, JpaSpecificationExecutor<Bonus>  {
	
	

}
