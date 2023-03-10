package com.alejandro.animeninja.bussines.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.ClaveBonus;
import com.alejandro.animeninja.bussines.model.dto.BonusAtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusDTO;
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

	@Override
	public List<Bonus> getBonusCombination(Specification<Bonus> specification) {
		List<Bonus> bonuses = bonusRepository.findAll(specification);
		return bonuses;
	}

	@Override
	public Bonus getBonusById(ClaveBonus clave) {
		Optional<Bonus> miBonus = bonusRepository.findById(clave);
		return miBonus.isPresent() ? miBonus.get() : null;
	}

	@Override
	public List<Bonus> getBonusBySetStats(String parte, Long valor) {
		return bonusRepository.findBySets(valor);
	}

	@Override
	public BonusDTO mergeBonuses(List<BonusDTO> bonuses) {
		
		
		Map <BonusAtributoDTO, Long> mapa = new HashMap<>();
		
		for(BonusDTO bonus: bonuses) {
			for(BonusAtributoDTO b : bonus.getListaBonus()) {
				
				if(mapa.containsKey(b)) {
					mapa.put(b, mapa.get(b) + b.getValor());
				}else {
					mapa.put(b, b.getValor());
				}
			
			}
			
		}

		BonusDTO bonus = new BonusDTO();
		for(Map.Entry <BonusAtributoDTO, Long> entry : mapa.entrySet()) {
			entry.getKey().setValor(entry.getValue());
		}
		bonus.setListaBonus(new ArrayList <> (mapa.keySet()));
		
		return bonus;
	}
}
