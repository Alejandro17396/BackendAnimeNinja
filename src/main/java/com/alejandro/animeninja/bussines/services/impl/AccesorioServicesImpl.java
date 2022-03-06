package com.alejandro.animeninja.bussines.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.model.SetAccesorio;
import com.alejandro.animeninja.bussines.services.AccesorioServices;
import com.alejandro.animeninja.integration.repositories.AccesorioRepository;

@Service
public class AccesorioServicesImpl implements AccesorioServices {

	@Autowired
	private AccesorioRepository accesorioRepository;
	
	@Override
	public List<SetAccesorio> getAll() {
		return accesorioRepository.findAll();
	}

}
