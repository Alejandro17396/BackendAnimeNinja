package com.alejandro.animeninja.bussines.services;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.alejandro.animeninja.bussines.model.FormationNinja;
import com.alejandro.animeninja.bussines.model.Ninja;

import com.alejandro.animeninja.bussines.model.dto.NinjasDTO;

public interface NinjaService {

	List <Ninja> getAll();
	
	List <Ninja> getBySpecification(Specification <Ninja> specification);
	
	Ninja getNinja(String clave);
	
	List <FormationNinja> createNinjaFormation(Specification <Ninja> specification);
	
	
}
