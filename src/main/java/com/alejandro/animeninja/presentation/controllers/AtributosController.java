package com.alejandro.animeninja.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.services.AtributoServices;


@RestController
@CrossOrigin
@RequestMapping("/attributes")
public class AtributosController {
	
	@Autowired
	private AtributoServices atributoServices;

	@GetMapping
	public List<Atributo> getAll() {
		return atributoServices.getAll();
	}
}
