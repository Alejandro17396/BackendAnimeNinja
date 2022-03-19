package com.alejandro.animeninja.bussines.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.model.Parte;
import com.alejandro.animeninja.bussines.services.ParteServices;
import com.alejandro.animeninja.integration.repositories.ParteRepository;

@Service
public class ParteServicesImpl implements ParteServices {

	@Autowired
	private ParteRepository parteRepository;
	
	@Override
	public List<Parte> getAll() {
		// TODO Auto-generated method stub
		return parteRepository.findAll();
	}

	@Override
	public List<Parte> getPartesLike(String filter) {
		// TODO Auto-generated method stub
		return parteRepository.findByNombreLike(filter);
	}

	@Override
	public Parte getPartesByNombre(String nombre) {
		// TODO Auto-generated method stub
		Optional <Parte> miParte= parteRepository.findById(nombre);
		return miParte.isPresent() ? miParte.get() : null;
	}

	@Override
	public boolean hasBetterStats(Parte p1, Parte p2) {
		return p1.getValor() > p2.getValor() ? true : false;
	}
	
	
	
	

}
