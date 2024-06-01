package com.alejandro.animeninja.presentation.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.ParteAccesorio;
import com.alejandro.animeninja.bussines.model.dto.AtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusAccesorioAtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.FilterAccesoriePartsDTO;
import com.alejandro.animeninja.bussines.model.dto.FiltroItemsAccesorioDTO;
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
	
	@PostMapping("/filter")
	public List<ParteAccesorio> getPartsByFilter(@RequestBody(required = false) FilterAccesoriePartsDTO filtro) {
		
		
		System.out.println("He llamado aqui2");
		/*List<FiltroItemsAccesorioDTO> filtros = new ArrayList<>();
		FiltroItemsAccesorioDTO filtro1 = new FiltroItemsAccesorioDTO();
		List<String> tipos = new ArrayList<>();
		tipos.add("agility");
		tipos.add("chakra");
		BonusAccesorioAtributoDTO bonus = new BonusAccesorioAtributoDTO();
		bonus.setAtributo(new AtributoDTO("cc resistance"));
		filtro1.setTiposBonus(tipos);
		filtro1.setBonusAccesorioAtributo(bonus);
		
		FiltroItemsAccesorioDTO filtro2 = new FiltroItemsAccesorioDTO();
		List<String> tipos2 = new ArrayList<>();
		tipos2.add("chakra");
		BonusAccesorioAtributoDTO bonus2 = new BonusAccesorioAtributoDTO();
		bonus2.setAtributo(new AtributoDTO("cc rate"));
		filtro2.setTiposBonus(tipos2);
		filtro2.setBonusAccesorioAtributo(bonus2);
		
		filtros.add(filtro1);
		filtros.add(filtro2);
		
		filtro = new FilterAccesoriePartsDTO();
		filtro.setFiltros(filtros);
		
		System.out.println("He llamado aqui");*/
		
		List<ParteAccesorio> partes = parteServices.findItemsByFilter(filtro);
		return partes;
	}


}
