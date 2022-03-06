package com.alejandro.animeninja.presentation.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.services.EquipoServices;


@RestController
@RequestMapping("/equipos")
public class EquipoController {

	
	@Autowired
	private EquipoServices equipoServices;
	
	@GetMapping
	public List<Equipo> getAll(){
		return equipoServices.getAll();
	}
	
	@GetMapping("/otro")
	public List<Equipo> getSetsByAttributes(){
		List<Atributo>attributes = new ArrayList<>();
		
		
		//attributes.add(new Atributo("attack"));
		//attributes.add(new Atributo("HP"));
		/*attributes.add(new Atributo("avoid injury rate"));
		attributes.add(new Atributo("damage rate"));*/
		//attributes.add(new Atributo("after using skill, recovers himself % HP by"));
		attributes.add(new Atributo("HP"));
		
		return equipoServices.getSetsByAttributes(attributes);
	}
}
