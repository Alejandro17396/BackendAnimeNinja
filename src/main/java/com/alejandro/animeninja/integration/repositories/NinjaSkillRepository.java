package com.alejandro.animeninja.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alejandro.animeninja.bussines.model.NinjaSkill;
import com.alejandro.animeninja.bussines.model.NinjaSkillKey;

public interface NinjaSkillRepository extends JpaRepository<NinjaSkill,NinjaSkillKey>{

}
