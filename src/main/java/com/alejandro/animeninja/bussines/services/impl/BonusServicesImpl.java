package com.alejandro.animeninja.bussines.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.services.BonusServices;
import com.alejandro.animeninja.integration.repositories.BonusRepository;

@Service
public class BonusServicesImpl implements BonusServices {

	@Autowired
	private BonusRepository bonusRepository;
	
	
	@Override
	public List<Bonus> getAll() {
		BonusAtributo b = new BonusAtributo();
		b.setNombreAtributo("avoid injury rate");
		return bonusRepository.findAll();
	}


	@Override
	public List<Bonus> getBonusByAttributes(List<Atributo> atributos) {
		BonusAtributo b = new BonusAtributo();
		b.setNombreAtributo("avoid injury rate");
		return bonusRepository.findByListOfAtributtes(atributos);
	}


	@Override
	public List<Bonus> getBonusBySpecification(Specification<Bonus> specification) {
		return bonusRepository.findAll(specification);
	}

}
