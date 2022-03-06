package com.alejandro.animeninja.bussines.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.model.Parte;
import com.alejandro.animeninja.bussines.services.ParteServices;
import com.alejandro.animeninja.integration.repositories.ParteRepository;

@Service
public class ParteServicesImpl implements ParteServices {

	@Autowired
	private ParteRepository parteRespository;
	
	@Override
	public List<Parte> getAll() {
		// TODO Auto-generated method stub
		return parteRespository.findAll();
	}
	
	
	
	

}
