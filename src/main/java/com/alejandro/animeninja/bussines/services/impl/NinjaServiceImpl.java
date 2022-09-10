package com.alejandro.animeninja.bussines.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.mappers.NinjaMapper;
import com.alejandro.animeninja.bussines.model.Formation;
import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.dto.NinjasDTO;
import com.alejandro.animeninja.bussines.services.NinjaService;
import com.alejandro.animeninja.integration.repositories.NinjaRepository;

@Service
public class NinjaServiceImpl implements NinjaService {

	@Autowired
	private NinjaRepository ninjaRepository;

	@Autowired
	private NinjaMapper mapper;

	@Override
	public List<Ninja> getAll() {
		return ninjaRepository.findAll();
	}

	@Override
	public Ninja getNinja(String name) {
		Optional<Ninja> ninja = ninjaRepository.findById(name);
		return ninja.isPresent() ? ninja.get() : null;
	}

	@Override
	public List<Ninja> getBySpecification(Specification<Ninja> specification) {
		return ninjaRepository.findAll(specification);
	}

	@Override
	public List<NinjasDTO> createNinjaFormation(Specification<Ninja> specification) {
		List<NinjasDTO> formations = new ArrayList<>();
		List<Ninja> ninjas = ninjaRepository.findAll(specification);
		if (ninjas.size() <= 4) {
			NinjasDTO aux = new NinjasDTO();
			aux.setNinjas(mapper.toDtoList(ninjas));
			aux.setNumber(ninjas.size());
			formations.add(aux);
			return formations;
		}

		List<Ninja> assaulters = ninjas.stream()
				.filter(ninja -> (ninja.getFormation() == Formation.ASSAULTER) ? true : false)
				.collect(Collectors.toList());
		List<Ninja> supports = ninjas.stream()
				.filter(ninja -> (ninja.getFormation() == Formation.SUPPORT) ? true : false)
				.collect(Collectors.toList());
		List<Ninja> vanguards = ninjas.stream()
				.filter(ninja -> (ninja.getFormation() == Formation.SUPPORT) ? true : false)
				.collect(Collectors.toList());
		formations = createFormations(assaulters, supports, vanguards);

		return formations;
	}

	private List<NinjasDTO> createFormations(List<Ninja> assaulters, List<Ninja> supports, List<Ninja> vanguards) {

		List<NinjasDTO> formations = new ArrayList<>();
		
		
		for(int i = 0 ; i < supports.size() ; ) {
			Ninja ninja = supports.get(i);
			supports.remove(ninja);
			NinjasDTO aux = createCombo(ninja,supports,assaulters,vanguards);
		}
		
		return formations;
	}

	private NinjasDTO createCombo(Ninja ninja, List<Ninja> supports, List<Ninja> assaulters, List<Ninja> vanguards) {
		
		List<NinjasDTO> formations = new ArrayList<>();
		List<Ninja> ninjas = new ArrayList<>();
		ninjas.add(ninja);
		NinjasDTO aux = new NinjasDTO();
		aux.setNinjas(new ArrayList<>());
		for(int i = 0 ; i < supports.size() ; ) {
			Ninja ninja2 = supports.get(i);
			NinjasDTO aux2 = createCombo(ninja,supports,assaulters,vanguards);
		}
		
		
		
		
		
		return null;
	}

}
