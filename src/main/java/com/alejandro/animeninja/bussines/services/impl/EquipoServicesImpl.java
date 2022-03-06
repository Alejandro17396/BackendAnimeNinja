package com.alejandro.animeninja.bussines.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.services.EquipoServices;
import com.alejandro.animeninja.integration.repositories.EquipoRepository;

@Service
public class EquipoServicesImpl implements EquipoServices {
	
	@Autowired
	private EquipoRepository equiposRepository;

	@Override
	public List<Equipo> getAll() {
		// TODO Auto-generated method stub
		List <String> atributos= new ArrayList<>();
		atributos.add("avoid injury rate");
		
		return equiposRepository.findAll();
	}
	
	@Override
	public List <Equipo> getSetsByAttributes(List <Atributo> attributes){
		//return equiposRepository.findByListOfAtributtes(null);
		/*attributes = new ArrayList<>();
		attributes.add(new Atributo("HP"));
		attributes.add(new Atributo("after using skill, recovers himself % HP by"));*/
		return equiposRepository.findByListOfAtributtes(attributes);
	}

}
