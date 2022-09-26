package com.alejandro.animeninja.bussines.validators;

import java.util.List;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.CreateComboSet;
import com.alejandro.animeninja.bussines.model.CreateComboSetAccesorio;
import com.alejandro.animeninja.bussines.model.dto.AttackSkillDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateComboNinjaDTO;

public interface ValidatorNinjaService {

	void validateCreateComboNinjaDTO(CreateComboNinjaDTO field);
	
	void validateAttackSkillDTO(AttackSkillDTO field);
	
	void validateCreateComboSet(CreateComboSet field);
	
	void validateCreateComboSetAccesorio(CreateComboSetAccesorio field);
	
	void validateAttributesList(List <Atributo> attributes);
	
}
