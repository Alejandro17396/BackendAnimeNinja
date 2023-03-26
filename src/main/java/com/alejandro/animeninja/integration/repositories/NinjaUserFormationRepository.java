package com.alejandro.animeninja.integration.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alejandro.animeninja.bussines.model.NinjaUserFormation;
import com.alejandro.animeninja.bussines.model.dto.FinalSkillsAttributesDTO;
import com.alejandro.animeninja.bussines.model.dto.FormationNinjaDTO;

public interface NinjaUserFormationRepository extends JpaRepository<NinjaUserFormation, Long>{
	
	Optional <NinjaUserFormation> findByIdAndUsername(Long id,String username);
	Optional <NinjaUserFormation> findByNombreAndUsername(String nombre,String username);
	List <NinjaUserFormation> findByUsername(String username);
}
