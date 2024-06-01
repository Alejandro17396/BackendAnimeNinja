package com.alejandro.animeninja.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.mappers.ParteAccesorioMapper;
import com.alejandro.animeninja.bussines.mappers.ParteMapper;
import com.alejandro.animeninja.bussines.model.Parte;
import com.alejandro.animeninja.bussines.model.ParteAccesorio;
import com.alejandro.animeninja.bussines.model.dto.EquipoConBonusDTO;
import com.alejandro.animeninja.bussines.model.dto.FilterAccesoriePartsDTO;
import com.alejandro.animeninja.bussines.model.dto.FilterEquipmentPartsDTO;
import com.alejandro.animeninja.bussines.model.dto.ParteAccesorioDTO;
import com.alejandro.animeninja.bussines.model.dto.ParteDTO;
import com.alejandro.animeninja.bussines.services.ParteAccesorioService;
import com.alejandro.animeninja.bussines.services.ParteServices;

@RestController
@CrossOrigin
@RequestMapping("/items")
public class ParteController {

	@Autowired
	private ParteServices parteServices;
	
	@Autowired
	private ParteAccesorioService parteAccesorioServices;
	
	@Autowired
	private ParteMapper equipmentPartsMapper;
	
	@Autowired
	private ParteAccesorioMapper accesoriesPartsMapper;

	@GetMapping("/equipment")
	public List<ParteDTO> getAll() {
		return equipmentPartsMapper.toListParteDTO(parteServices.getAll());
	}
	
	@GetMapping("/accesories")
	public List<ParteAccesorioDTO> getAllAccesories() {
		return accesoriesPartsMapper.toListParteDTO(parteAccesorioServices.getAll());
	}

	@GetMapping("/like/{filter}")
	public List<Parte> getLike(@PathVariable String filter) {
		return parteServices.getPartesLike("%" + filter + "%");
	}

	@GetMapping("/{nombre}")
	public Parte getByNombre(@PathVariable String nombre) {
		return parteServices.getPartesByNombre(nombre);
	}
	
	
	@PostMapping("/filter")
	public List<EquipoConBonusDTO> getPartByBonuses(@RequestBody(required = false) FilterEquipmentPartsDTO filtro) {
		return parteServices.getPartsByBonuses(filtro);
	}

}
