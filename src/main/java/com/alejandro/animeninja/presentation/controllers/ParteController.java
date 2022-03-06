package com.alejandro.animeninja.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.Parte;
import com.alejandro.animeninja.bussines.services.EquipoServices;
import com.alejandro.animeninja.bussines.services.ParteServices;

@RestController
@RequestMapping("/partes")
public class ParteController {
	
	@Autowired
	private ParteServices parteServices;
	
	@GetMapping
	public List<Parte> getAll(){
		return parteServices.getAll();
	}

}
