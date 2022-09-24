package com.alejandro.animeninja.presentation.controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.annotation.PageableConstraint;
import com.alejandro.animeninja.bussines.mappers.NinjaMapper;
import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.Pagination;
import com.alejandro.animeninja.bussines.model.dto.CreateComboNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.FormationNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.FormationsNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjasDTO;
import com.alejandro.animeninja.bussines.services.NinjaService;
import com.alejandro.animeninja.bussines.validators.ValidatorNinjaService;

@RestController
@CrossOrigin
@RequestMapping("/ninjas")
public class NinjasController {

	@Autowired
	private NinjaService ninjaServices;

	@Autowired
	private NinjaMapper ninjaMapper;
	
	@Autowired
	private ValidatorNinjaService validator;

	
	
	@GetMapping
	public NinjasDTO getNinjaPaged(Pageable pageable) {

		NinjasDTO response = new NinjasDTO();
		response.setNinjas(ninjaMapper.toDtoList(ninjaServices.getAllPaged(pageable).getContent()));
		response.setNumber(response.getNinjas().size());
		return response;
		
	}

	@GetMapping("/filter/and")
	public ResponseEntity <NinjasDTO> getNinjaFiltroAnd(@RequestBody(required = false) CreateComboNinjaDTO attributes,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
			Pageable pageable) {

		validator.validateCreateComboNinjaDTO(attributes);
		
		NinjasDTO responseDTO = new NinjasDTO();
		responseDTO.setNinjas(ninjaServices.getNinjaFiltroAnd(attributes, sorted, filtred,pageable));
		responseDTO.setNumber(responseDTO.getNinjas().size());
		
		
		ResponseEntity <NinjasDTO> response = null;
		if(responseDTO.getNumber() > 0) {
			response = new ResponseEntity <>(responseDTO,HttpStatus.OK);
		}else {
			response = new ResponseEntity <>(responseDTO,HttpStatus.NO_CONTENT);
		}
		
		return response;
	}
	
	@GetMapping("/filter/or")
	public ResponseEntity <NinjasDTO> getNinjaFiltroOr(@RequestBody(required = false) CreateComboNinjaDTO attributes,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
			@Valid @PageableConstraint Pageable pageable) {

		validator.validateCreateComboNinjaDTO(attributes);
		
		NinjasDTO responseDTO = new NinjasDTO();
		responseDTO.setNinjas(ninjaServices.getNinjaFiltroOr(attributes, sorted, filtred,pageable));
		responseDTO.setNumber(responseDTO.getNinjas().size());
		
		ResponseEntity <NinjasDTO> response = null;
		if(responseDTO.getNumber() > 0) {
			response = new ResponseEntity <>(responseDTO,HttpStatus.OK);
		}else {
			response = new ResponseEntity <>(responseDTO,HttpStatus.NO_CONTENT);
		}
		
		return response;
	}
	
	@GetMapping("/createformations")
	public ResponseEntity <FormationsNinjaDTO> getNinjaComboFormations(
			@RequestBody(required = false) CreateComboNinjaDTO externFilter,
			@RequestParam(value = "merge", required = false, defaultValue = "true") boolean merge,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred,
			@RequestParam(value = "or", required = false, defaultValue = "true") boolean or,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize) {
 
		ResponseEntity <FormationsNinjaDTO> response = null;
		FormationsNinjaDTO responseDTO = new FormationsNinjaDTO();
		validator.validateCreateComboNinjaDTO(externFilter);
		
		Pagination <FormationNinjaDTO> pagination =  new Pagination <FormationNinjaDTO> 
		(ninjaServices.getNinjaComboFormations(externFilter, merge, sorted, filtred, or),page,pageSize);
		responseDTO.setFormations(pagination.getPagedList());
		responseDTO.setNumFormations(responseDTO.getFormations().size());
		
		 
		if(responseDTO.getNumFormations() > 0) {
			response = new ResponseEntity <>(responseDTO,HttpStatus.OK);
		}else {
			response = new ResponseEntity <>(responseDTO,HttpStatus.NO_CONTENT);
		}

		return response;
	}
	
	public void showHeapMemory() {
		 int dataSize = 1024 * 1024;
		 Runtime runtime = Runtime.getRuntime();
		 System.out.println ("Memoria máxima: " + runtime.maxMemory() / dataSize + "MB");
		 System.out.println ("Memoria total: " + runtime.totalMemory() / dataSize + "MB");
		 System.out.println ("Memoria libre: " + runtime.freeMemory() / dataSize + "MB");
		 System.out.println ("Memoria usada: " + (runtime.totalMemory() - runtime.freeMemory()) / dataSize + "MB");
	}
	
	

}
