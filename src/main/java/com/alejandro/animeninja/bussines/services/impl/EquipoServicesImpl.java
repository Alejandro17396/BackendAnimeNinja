package com.alejandro.animeninja.bussines.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.services.EquipoServices;
import com.alejandro.animeninja.integration.repositories.BonusRepository;
import com.alejandro.animeninja.integration.repositories.EquipoRepository;

@Service
public class EquipoServicesImpl implements EquipoServices {
	
	@Autowired
	private EquipoRepository equiposRepository;
	
	@Autowired
	private BonusRepository bonusRepository;

	@Override
	public List<Equipo> getAll() {
		return equiposRepository.findAll();
	}
	
	@Override
	public List <Equipo> getSetsByAttributes(List <Atributo> attributes){
		return equiposRepository.findByListOfAtributtes(attributes);
	}
	
	@Override
	public List <Equipo> getSetsBySpecification(Specification <Equipo> specification){
		return equiposRepository.findAll(specification);
	}
	
	

	@Override
	public List<Equipo> generateCombinationSetsByBonus(Specification<Bonus> specification) {
		List <Bonus> bonuses = bonusRepository.findAll(specification);
		List <Equipo> equipos = new ArrayList <>();
		
		
		
		return null;
	}

}
