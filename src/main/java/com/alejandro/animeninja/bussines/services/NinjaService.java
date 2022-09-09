package com.alejandro.animeninja.bussines.services;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.alejandro.animeninja.bussines.model.Ninja;

public interface NinjaService {

	List <Ninja> getAll();
	
	List <Ninja> getBySpecification(Specification <Ninja> specification);
	
	Ninja getNinja(String clave);
	
	
}
