package com.alejandro.animeninja.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.model.SetAccesorio;
import com.alejandro.animeninja.bussines.services.AccesorioServices;

@RestController
@RequestMapping("/accesorios")
public class AccesoriosController {

	
	@Autowired
	private AccesorioServices accesorioServices;
	
	@GetMapping
	public List<SetAccesorio> getAll(){
		return accesorioServices.getAll();
	}
}
