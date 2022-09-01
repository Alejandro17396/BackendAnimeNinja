package com.alejandro.animeninja.presentation.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.model.AttributeStat;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.NinjaSkill;
import com.alejandro.animeninja.bussines.services.AttributeStatService;
import com.alejandro.animeninja.bussines.services.EquipoServices;
import com.alejandro.animeninja.bussines.services.NinjaService;
import com.alejandro.animeninja.bussines.sort.services.impl.SortEquiposByStats;
import com.alejandro.animeninja.integration.repositories.AttributeStatRepository;
import com.alejandro.animeninja.integration.repositories.NinjaSkillRepository;

@RestController
@CrossOrigin
@RequestMapping("/ninjas")
public class NinjasController {
	
	@Autowired
	private NinjaService ninjaServices;

	@GetMapping
	public List<Ninja> getAll() {
		
		List <Ninja> ninjas = ninjaServices.getAll();
		return ninjas;
	}
	
	@Autowired
	private AttributeStatService at;
	
	@GetMapping("/stats")
	public List<AttributeStat> getstat() {
		
		List <AttributeStat> ninjas = at.getAll();
		return ninjas;
	}
	
	@Autowired
	private NinjaSkillRepository rep;
	
	@GetMapping("/skills")
	public List<NinjaSkill> getskill() {
		
		List <NinjaSkill> ninjas = rep.findAll();
		return ninjas;
	}

}
