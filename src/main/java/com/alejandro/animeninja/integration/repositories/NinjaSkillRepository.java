package com.alejandro.animeninja.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alejandro.animeninja.bussines.model.NinjaSkill;
import com.alejandro.animeninja.bussines.model.NinjaSkillKey;
import com.alejandro.animeninja.bussines.model.SkillType;

public interface NinjaSkillRepository extends JpaRepository<NinjaSkill,NinjaSkillKey>{
	
	NinjaSkill findByNinjaAndType(String ninja,SkillType type );

}
