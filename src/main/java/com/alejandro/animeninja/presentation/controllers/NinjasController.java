package com.alejandro.animeninja.presentation.controllers;


import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.mappers.FormationNinjaMapper;
import com.alejandro.animeninja.bussines.mappers.NinjaMapper;
import com.alejandro.animeninja.bussines.model.FormationNinja;
import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.dto.CreateComboNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.FormationNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.FormationsNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaFilterDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjasDTO;
import com.alejandro.animeninja.bussines.model.dto.SkillAttributeDTO;
import com.alejandro.animeninja.bussines.services.NinjaService;
import com.alejandro.animeninja.integration.specifications.NinjaSpecification;

@RestController
@CrossOrigin
@RequestMapping("/ninjas")
public class NinjasController {

	@Autowired
	private NinjaService ninjaServices;

	@Autowired
	private NinjaMapper ninjaMapper;

	@GetMapping
	public NinjasDTO getNinja() {

		List<Ninja> ninjas = ninjaServices.getAll();
		NinjasDTO response = new NinjasDTO();
		response.setNinjas(ninjaMapper.toDtoList(ninjas));
		response.setNumber(ninjas.size());
		return response;
	}

	@GetMapping("/filtro/and")
	public NinjasDTO getNinjaFiltroAnd(@RequestBody(required = false) CreateComboNinjaDTO attributes,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred) {

		NinjasDTO response = new NinjasDTO();
		response.setNinjas(ninjaServices.getNinjaFiltroAnd(attributes, sorted, filtred));
		response.setNumber(response.getNinjas().size());
		return response;
	}

	@GetMapping("/filtro/or")
	public NinjasDTO getNinjaFiltroOr(@RequestBody(required = false) CreateComboNinjaDTO attributes,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred) {

	
		NinjasDTO response = new NinjasDTO();
		response.setNinjas(ninjaServices.getNinjaFiltroOr(attributes, sorted, filtred));
		response.setNumber(response.getNinjas().size());
		return response;
	}
	
	@GetMapping("/createCombo")
	public FormationsNinjaDTO getNinjaComboFormations(@RequestBody(required = false) CreateComboNinjaDTO attributes,
			@RequestParam(value = "merge", required = false, defaultValue = "true") boolean merge,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred,
			@RequestParam(value = "or", required = false, defaultValue = "true") boolean or) {

		FormationsNinjaDTO response = new FormationsNinjaDTO();
		response.setFormations(ninjaServices.getNinjaComboFormations(attributes, merge, sorted, filtred, or));
		response.setNumFormations(response.getFormations().size());
		return response;
	}
	
	/*List <List <SkillAttributeDTO>> response2 = new ArrayList<>();
	
	for(FormationNinjaDTO element : response.getFormations()) {
		response2.add(element.getMergedAtributes());
	}
	System.out.println(response2.size());*/

}
