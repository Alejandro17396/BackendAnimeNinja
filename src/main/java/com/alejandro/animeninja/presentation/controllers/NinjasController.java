package com.alejandro.animeninja.presentation.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.mappers.NinjaMapper;
import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.dto.CreateComboNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.FormationsNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjasDTO;
import com.alejandro.animeninja.bussines.services.NinjaService;
import com.alejandro.animeninja.bussines.services.ValidatorService;

@RestController
@CrossOrigin
@RequestMapping("/ninjas")
public class NinjasController {

	@Autowired
	private NinjaService ninjaServices;

	@Autowired
	private NinjaMapper ninjaMapper;
	
	@Autowired
	private ValidatorService validator;

	@GetMapping
	public NinjasDTO getNinja() {

		List<Ninja> ninjas = ninjaServices.getAll();
		NinjasDTO response = new NinjasDTO();
		response.setNinjas(ninjaMapper.toDtoList(ninjas));
		response.setNumber(response.getNinjas().size());
		return response;
	}

	@GetMapping("/filtro/and")
	public ResponseEntity <NinjasDTO> getNinjaFiltroAnd(@RequestBody(required = false) CreateComboNinjaDTO attributes,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred) {

		validator.validateCreateComboNinjaDTO(attributes);
		
		NinjasDTO responseDTO = new NinjasDTO();
		responseDTO.setNinjas(ninjaServices.getNinjaFiltroAnd(attributes, sorted, filtred));
		responseDTO.setNumber(responseDTO.getNinjas().size());
		
		ResponseEntity <NinjasDTO> response = null;
		if(responseDTO.getNumber() > 0) {
			response = new ResponseEntity <>(responseDTO,HttpStatus.OK);
		}else {
			response = new ResponseEntity <>(responseDTO,HttpStatus.NO_CONTENT);
		}
		
		return response;
	}

	@GetMapping("/filtro/or")
	public ResponseEntity <NinjasDTO> getNinjaFiltroOr(@RequestBody(required = false) CreateComboNinjaDTO attributes,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred) {

		validator.validateCreateComboNinjaDTO(attributes);
		
		NinjasDTO responseDTO = new NinjasDTO();
		responseDTO.setNinjas(ninjaServices.getNinjaFiltroOr(attributes, sorted, filtred));
		responseDTO.setNumber(responseDTO.getNinjas().size());
		
		ResponseEntity <NinjasDTO> response = null;
		if(responseDTO.getNumber() > 0) {
			response = new ResponseEntity <>(responseDTO,HttpStatus.OK);
		}else {
			response = new ResponseEntity <>(responseDTO,HttpStatus.NO_CONTENT);
		}
		
		return response;
	}
	
	@GetMapping("/createCombo")
	public ResponseEntity <FormationsNinjaDTO> getNinjaComboFormations(@RequestBody(required = false) CreateComboNinjaDTO attributes,
			@RequestParam(value = "merge", required = false, defaultValue = "true") boolean merge,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred,
			@RequestParam(value = "or", required = false, defaultValue = "true") boolean or) {
 
		validator.validateCreateComboNinjaDTO(attributes);
		
		FormationsNinjaDTO responseDTO = new FormationsNinjaDTO();
		responseDTO.setFormations(ninjaServices.getNinjaComboFormations(attributes, merge, sorted, filtred, or));
		responseDTO.setNumFormations(responseDTO.getFormations().size());
		ResponseEntity <FormationsNinjaDTO> response = null;
		if(responseDTO.getNumFormations() > 0) {
			response = new ResponseEntity <>(responseDTO,HttpStatus.OK);
		}else {
			response = new ResponseEntity <>(responseDTO,HttpStatus.NO_CONTENT);
		}
		
		return response;
	}
	
	
	
	/*List <List <SkillAttributeDTO>> response2 = new ArrayList<>();
	
	for(FormationNinjaDTO element : response.getFormations()) {
		response2.add(element.getMergedAtributes());
	}
	System.out.println(response2.size());*/

}
