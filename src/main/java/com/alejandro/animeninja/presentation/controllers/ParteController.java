package com.alejandro.animeninja.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.model.Parte;
import com.alejandro.animeninja.bussines.services.ParteServices;

@RestController
@CrossOrigin
@RequestMapping("/partes")
public class ParteController {

	@Autowired
	private ParteServices parteServices;

	@GetMapping
	public List<Parte> getAll() {
		return parteServices.getAll();
	}

	@GetMapping("/like/{filter}")
	public List<Parte> getLike(@PathVariable String filter) {
		return parteServices.getPartesLike("%" + filter + "%");
	}

	@GetMapping("/{nombre}")
	public Parte getByNombre(@PathVariable String nombre) {
		return parteServices.getPartesByNombre(nombre);
	}

}
