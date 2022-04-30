package com.alejandro.animeninja.presentation.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.services.BonusServices;
import com.alejandro.animeninja.integration.specifications.BonusSpecification;

@RestController
@CrossOrigin
@RequestMapping("/bonuses")
public class BonusController {

	@Autowired
	private BonusServices bonusServices;

	@GetMapping
	public List<Bonus> getAll() {
		return bonusServices.getAll();
	}
	
	@GetMapping("/sets")
	public List<Bonus> getAll2() {
		return bonusServices.getBonusBySetStats("kunai", 56000L);
	}

	@GetMapping("/otro") 
	public List<Bonus> getSetsByAttributes() {
		List<Atributo> attributes = new ArrayList<>();

		// attributes.add(new Atributo("attack"));
		// attributes.add(new Atributo("HP"));
		/* attributes.add(new Atributo("avoid injury rate")); */
		// attributes.add(new Atributo("damage rate"));
		attributes.add(new Atributo("after using skill, recovers himself % HP by"));
		attributes.add(new Atributo("HP"));

		return bonusServices.getBonusByAttributes(attributes);
	}

	@GetMapping("/specification")
	public List<Bonus> getSetsByAttributesSpecification() {
		List<Atributo> attributes = new ArrayList<>();

		// attributes.add(new Atributo("attack"));
		// attributes.add(new Atributo("HP"));
		/* attributes.add(new Atributo("avoid injury rate")); */
		// attributes.add(new Atributo("damage rate"));
		attributes.add(new Atributo("after using skill, recovers himself % HP by"));
		attributes.add(new Atributo("HP"));

		Specification<Bonus> specification = Specification.where(null);
		for (Atributo a : attributes) {
			specification = specification.and(BonusSpecification.existBonusAtributoByAttribute(a));
		}

		return bonusServices.getBonusByAttributes(attributes);
	}

	@GetMapping("/Join")
	public List<Bonus> getSetsByAttributesSpecification2() {

		List<Atributo> attributes = new ArrayList<>();

		// attributes.add(new Atributo("attack"));
		// attributes.add(new Atributo("HP"));
		/* attributes.add(new Atributo("avoid injury rate")); */
		// attributes.add(new Atributo("damage rate"));
		// attributes.add(new Atributo("after using skill, recovers himself % HP by"));
		attributes.add(new Atributo("HP"));

		Specification<Bonus> specification = Specification.where(null);
		for (Atributo a : attributes) {
			specification = specification.and(BonusSpecification.existBonusAtributoByAttribute(a));
		}

		return bonusServices.getBonusCombination(specification);
	}

	
	@GetMapping("/GetFinalBonus")
	public List<Bonus> getBestBonusCombos() {

		List<Atributo>attributes = new ArrayList<>();
		
		attributes.add(new Atributo("attack"));
		//attributes.add(new Atributo("HP"));
		attributes.add(new Atributo("avoid injury rate"));
		attributes.add(new Atributo("damage rate"));
		//attributes.add(new Atributo("after using skill, recovers himself % HP by"));
		attributes.add(new Atributo("HP"));
		
		Specification <Bonus> specification = Specification.where(null);
		for(Atributo a : attributes) {
			specification=specification.or(BonusSpecification.existBonusAtributoByAttribute(a));
		}
		//List <Equipo> equipos = equipoServices.generateCombinationSetsByBonus(specification,attributes);
		//System.out.println(equipos.size());
		/*List<Atributo> attributes = new ArrayList<>();

		// attributes.add(new Atributo("attack"));
		// attributes.add(new Atributo("HP"));
		/* attributes.add(new Atributo("avoid injury rate")); */
		// attributes.add(new Atributo("damage rate"));
		// attributes.add(new Atributo("after using skill, recovers himself % HP by"));
		/*attributes.add(new Atributo("HP"));

		Specification<Bonus> specification = Specification.where(null);
		for (Atributo a : attributes) {
			specification = specification.and(BonusSpecification.existBonusAtributoByAttribute(a));
		}*/

		return bonusServices.getBonusCombination(specification);
	}
}
