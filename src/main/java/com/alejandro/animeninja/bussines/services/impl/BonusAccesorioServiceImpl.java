package com.alejandro.animeninja.bussines.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.model.BonusAccesorio;
import com.alejandro.animeninja.bussines.model.ClaveBonusAccesorio;
import com.alejandro.animeninja.bussines.services.BonusAccesorioService;
import com.alejandro.animeninja.integration.repositories.BonusAccesorioRepository;

@Service
public class BonusAccesorioServiceImpl implements BonusAccesorioService{

	@Autowired
	private BonusAccesorioRepository bonusAccesorioRepository;
	
	@Override
	public List<BonusAccesorio> getBonusBySpecification(Specification<BonusAccesorio> specification) {
		return bonusAccesorioRepository.findAll(specification);
	}

	@Override
	public List<BonusAccesorio> getAll() {
		return bonusAccesorioRepository.findAll();
	}

	@Override
	public List<BonusAccesorio> getBonusByParteBonus(Long valor) {
		return bonusAccesorioRepository.findBySets(50000L);
	}

	@Override
	public BonusAccesorio getBonusById(ClaveBonusAccesorio clave) {
		Optional <BonusAccesorio> optional = bonusAccesorioRepository.findById(clave);
		return optional.isPresent() ? optional.get() : null;
	}
	
	
	
}
