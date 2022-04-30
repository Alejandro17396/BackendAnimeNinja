package com.alejandro.animeninja.bussines.services.impl;

import java.util.List;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.services.AtributoServices;
import com.alejandro.animeninja.integration.repositories.AtributoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtributoServicesImpl implements AtributoServices {

	@Autowired
	private AtributoRepository atributoRepository;
	
	@Override
	public List<Atributo> getAll() {
		return atributoRepository.findAll();
	}

}
