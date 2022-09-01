package com.alejandro.animeninja.bussines.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.model.AttributeStat;
import com.alejandro.animeninja.bussines.services.AttributeStatService;
import com.alejandro.animeninja.integration.repositories.AttributeStatRepository;

@Service
public class AttributeStatServiceImpl implements AttributeStatService {

	@Autowired
	private AttributeStatRepository repository;
	
	@Override
	public List<AttributeStat> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

}
