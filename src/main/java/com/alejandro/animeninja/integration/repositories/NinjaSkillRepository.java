package com.alejandro.animeninja.integration.repositories;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alejandro.animeninja.bussines.model.NinjaSkill;
import com.alejandro.animeninja.bussines.model.NinjaSkillKey;
import com.alejandro.animeninja.bussines.model.SkillType;

public interface NinjaSkillRepository extends JpaRepository<NinjaSkill,NinjaSkillKey>{
	
	@LazyCollection(LazyCollectionOption.FALSE)
	NinjaSkill findByNinjaAndType(String ninja,SkillType type );

}
