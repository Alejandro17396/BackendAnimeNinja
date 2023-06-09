package com.alejandro.animeninja.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.model.ParteAccesorio;
import com.alejandro.animeninja.bussines.services.ParteAccesorioService;

@RestController
@CrossOrigin
@RequestMapping("/accesoriesSetItems")
public class AccesoriesPartsController {
	
	@Autowired
	private ParteAccesorioService parteServices;
	
	@GetMapping
	public List<ParteAccesorio> getAll() {
		return parteServices.getAll();
	}


}
