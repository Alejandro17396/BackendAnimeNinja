package com.alejandro.animeninja.bussines.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
