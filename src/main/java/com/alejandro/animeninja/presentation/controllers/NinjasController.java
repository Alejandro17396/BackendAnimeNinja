package com.alejandro.animeninja.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.mappers.NinjaMapper;
import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.dto.NinjaDTO;
import com.alejandro.animeninja.bussines.services.NinjaService;

@RestController
@CrossOrigin
@RequestMapping("/ninjas")
public class NinjasController {
	
	@Autowired
	private NinjaService ninjaServices;
	
	@Autowired
	private NinjaMapper mapper;

	@GetMapping
	public List<Ninja> getAll() {
		
		List <Ninja> ninjas = ninjaServices.getAll();
		return ninjas;
	}
	
	@GetMapping("/dto")
	public List <NinjaDTO> getNinja() {
		
		List <Ninja> ninjas = ninjaServices.getAll();
		return mapper.toDtoList(ninjas);
	}

}
