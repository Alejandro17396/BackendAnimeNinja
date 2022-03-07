package com.alejandro.animeninja.presentation.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.services.BonusServices;

@RestController
@RequestMapping("/bonuses")
public class BonusController {
	
	@Autowired
	private BonusServices bonusServices;
	
	@GetMapping
	public List<Bonus> getAll(){
		return bonusServices.getAll();
	}
	
	@GetMapping("/otro")
	public List<Bonus> getSetsByAttributes(){
		List<Atributo>attributes = new ArrayList<>();
		
		
		//attributes.add(new Atributo("attack"));
		//attributes.add(new Atributo("HP"));
		/*attributes.add(new Atributo("avoid injury rate"));*/
		//attributes.add(new Atributo("damage rate"));
		attributes.add(new Atributo("after using skill, recovers himself % HP by"));
		attributes.add(new Atributo("HP"));
		
		return bonusServices.getBonusByAttributes(attributes);
	}

}
