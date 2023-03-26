package com.alejandro.animeninja.bussines.services;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.ClaveBonus;
import com.alejandro.animeninja.bussines.model.dto.BonusDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaUserFormationDTO;
import com.alejandro.animeninja.bussines.model.dto.SkillAttributeDTO;
import com.alejandro.animeninja.bussines.model.Atributo;

public interface BonusServices {

	List<Bonus> getAll();

	List<Bonus> getBonusByAttributes(List<Atributo> atributos);

	List<Bonus> getBonusBySpecification(Specification<Bonus> specification);

	List<Bonus> getBonusCombination(Specification<Bonus> specification);
	
	List<Bonus> getBonusBySet(String name);
	
	Bonus getBonusById(ClaveBonus clave);

	List<Bonus> getBonusBySetStats(String parte,Long valor);
	
	BonusDTO mergeBonuses(List<BonusDTO> bonuses,String name);
	
	Bonus mergeBonusesEntity(List<Bonus> bonuses);
	
	List <BonusDTO> mergeNinjaSetAndAccesorieBonuses(NinjaUserFormationDTO ninja);
	
	BonusDTO mergeBonusesNinja(List<BonusDTO> bonuses);
	
	List<BonusDTO> parseBonusFormation(List<SkillAttributeDTO> mergedTalentAttributes);
	
	List<BonusDTO> mergeAllBonuses(NinjaUserFormationDTO ninja);
	
	List <BonusDTO> mergeFormationAndNinjaBonuses(List<BonusDTO> bonuses, NinjaUserFormationDTO ninja);
}
