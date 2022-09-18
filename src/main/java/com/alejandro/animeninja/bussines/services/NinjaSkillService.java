package com.alejandro.animeninja.bussines.services;

import java.util.List;

import com.alejandro.animeninja.bussines.model.NinjaSkill;
import com.alejandro.animeninja.bussines.model.NinjaSkillKey;
import com.alejandro.animeninja.bussines.model.SkillAttribute;
import com.alejandro.animeninja.bussines.model.SkillType;

public interface NinjaSkillService {
	
	List <NinjaSkill> getAll();
	
	NinjaSkill getById(NinjaSkillKey key);

	List <SkillAttribute> createSkill(List<NinjaSkillKey> skillsKeys);
	
	List <SkillAttribute> createSkill2(List<NinjaSkillKey> skillsKeys);
	
	NinjaSkill findByNinjaAndType(String name,SkillType type);
}
