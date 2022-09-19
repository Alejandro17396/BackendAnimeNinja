package com.alejandro.animeninja.bussines.services;

import com.alejandro.animeninja.bussines.model.CreateComboSet;
import com.alejandro.animeninja.bussines.model.dto.AttackSkillDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateComboNinjaDTO;

public interface ValidatorService {

	void validateCreateComboNinjaDTO(CreateComboNinjaDTO field);
	
	void validateAttackSkillDTO(AttackSkillDTO field);
	
	void validateCreateComboSet(CreateComboSet field);
	
}
