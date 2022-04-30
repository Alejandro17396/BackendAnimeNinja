package com.alejandro.animeninja.bussines.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.model.BonusAccesorio;
import com.alejandro.animeninja.bussines.model.ParteAccesorio;
import com.alejandro.animeninja.bussines.services.ParteAccesorioService;
import com.alejandro.animeninja.integration.repositories.ParteAccesorioRepository;

@Service
public class ParteAccesorioServiceImpl implements ParteAccesorioService {

	@Autowired
	private ParteAccesorioRepository parteAccesorioRepository;
	
	@Override
	public List<ParteAccesorio> getAll() {
		return parteAccesorioRepository.findAll();
	}

	@Override
	public List<ParteAccesorio> getParteAccesorioByBonus(BonusAccesorio bonus) {
		// TODO Auto-generated method stub
		return parteAccesorioRepository.findByNombreSetAndTipo(bonus.getNombreAccesorioSet(), bonus.getTipo());
	}

	@Override
	public ParteAccesorio getById(String name)
	{
		Optional <ParteAccesorio> aux= parteAccesorioRepository.findById(name);
		return aux.isPresent()? aux.get():null;
	}
}
