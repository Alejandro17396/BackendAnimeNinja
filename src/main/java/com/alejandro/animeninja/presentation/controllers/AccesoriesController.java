package com.alejandro.animeninja.presentation.controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.CreateComboSetAccesorio;
import com.alejandro.animeninja.bussines.model.Pagination;
import com.alejandro.animeninja.bussines.model.dto.SetAccesorioDTO;
import com.alejandro.animeninja.bussines.model.dto.SetsAccesorioDTO;
import com.alejandro.animeninja.bussines.services.AccesorioServices;
import com.alejandro.animeninja.bussines.validators.ValidatorNinjaService;

@RestController
@CrossOrigin
@RequestMapping("/accesories")
public class AccesoriesController {

	@Autowired
	private AccesorioServices accesorioServices;
	
	@Autowired
	private ValidatorNinjaService validator;

	@GetMapping
	public ResponseEntity <Page <SetAccesorioDTO>> getAll(Pageable pageable) {
		Page <SetAccesorioDTO> responseDTO = accesorioServices.getAll(pageable);
		ResponseEntity <Page <SetAccesorioDTO>> response = null;
		
		if(responseDTO.getContent().size() > 0) {
			response = new ResponseEntity <>(responseDTO,HttpStatus.OK);
		}else {
			response = new ResponseEntity <>(responseDTO,HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@GetMapping("/filterAccesoriesSetByAttributes")
	public ResponseEntity <Page <SetAccesorioDTO>> getAll2(@Valid @RequestBody(required = false) List<Atributo> attributes,
			Pageable pageable) {

		validator.validateAttributesList(attributes);

		Page <SetAccesorioDTO> responseDTO = accesorioServices.getBySpecification(attributes,pageable);
		
		ResponseEntity <Page <SetAccesorioDTO>> response = null;
		
		if(responseDTO.getContent().size() > 0) {
			response = new ResponseEntity <>(responseDTO,HttpStatus.OK);
		}else {
			response = new ResponseEntity <>(responseDTO,HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@GetMapping("/getSet/{nombre}")
	public SetAccesorioDTO getSetById(@PathVariable String nombre) {
		return accesorioServices.getByNombre(nombre);
	}

	@GetMapping("/CombinacionesBonusTotal")
	public ResponseEntity <SetsAccesorioDTO> getAll4(@RequestBody(required = false) CreateComboSetAccesorio attributes,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred,
			@RequestParam(value = "hardSearch", required = false, defaultValue = "false") boolean hardSearch,
			Pageable pageable) {

		validator.validateCreateComboSetAccesorio(attributes);
		
		Pagination <SetAccesorioDTO> pagination =  new Pagination <SetAccesorioDTO> 
		(accesorioServices.getComboAccesoriosBySpecification(attributes,sorted,filtred, hardSearch,pageable),
				pageable.getPageNumber(),pageable.getPageSize());
		ResponseEntity <SetsAccesorioDTO> response = null;
		SetsAccesorioDTO responseDTO = new SetsAccesorioDTO();
		
		responseDTO.setSets(pagination.getPagedList());
		responseDTO.setNumber(pagination.getPagedList().size());
		 
		if(responseDTO.getNumber() > 0) {
			response = new ResponseEntity <>(responseDTO,HttpStatus.OK);
		}else {
			response = new ResponseEntity <>(responseDTO,HttpStatus.NO_CONTENT);
		}

		return response;
	}
	
}
