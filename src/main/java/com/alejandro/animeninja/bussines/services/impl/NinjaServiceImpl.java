package com.alejandro.animeninja.bussines.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.model.ClaveNinja;
import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.services.NinjaService;
import com.alejandro.animeninja.integration.repositories.NinjaRepository;

@Service
public class NinjaServiceImpl implements NinjaService {

	@Autowired
	private NinjaRepository ninjaRepository;
	
	@Override
	public List<Ninja> getAll() {
		return ninjaRepository.findAll();
	}

	@Override
	public Ninja getNinja(String name) {
		Optional <Ninja> ninja = ninjaRepository.findById(name);
		return ninja.isPresent()? ninja.get() : null;
	}

	@Override
	public List<Ninja> getBySpecification(Specification <Ninja> specification) {
		return ninjaRepository.findAll(specification);
	}

}
