package com.alejandro.animeninja.presentation.controllers;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.mappers.UserFormationMapper;
import com.alejandro.animeninja.bussines.model.UserFormation;
import com.alejandro.animeninja.bussines.model.dto.CreateUserFormationDTO;
import com.alejandro.animeninja.bussines.model.dto.UserFormationDTO;
import com.alejandro.animeninja.bussines.services.FormationService;
import com.alejandro.animeninja.bussines.validators.ValidatorUserFormation;
import com.alejandro.animeninja.integration.repositories.UserFormationRepository;


@RestController
@CrossOrigin
@RequestMapping("/formation")
public class FormationController {

	@Autowired
	private FormationService formationService;
	
	@Autowired
	private UserFormationRepository repository;
	
	@Autowired
	private UserFormationMapper userFormationMapper;
	
	@Autowired
	private ValidatorUserFormation validatorUserFormation;
	
	@GetMapping("/find/{id}")
	public ResponseEntity <UserFormationDTO> findFormation(@PathVariable Long id) throws InterruptedException, ExecutionException {

		UserFormationDTO result = userFormationMapper.toDTO(formationService.getUserFormationById(id));
		ResponseEntity <UserFormationDTO> responseDTO = null;
		
		if(result != null) {
			responseDTO = new ResponseEntity <>(result,HttpStatus.OK);
		}else {
			responseDTO = new ResponseEntity <>(null,HttpStatus.NO_CONTENT);
		}
		
		return responseDTO;
	}
	
	@PostMapping
	public ResponseEntity <UserFormationDTO> createFormation(@RequestBody CreateUserFormationDTO dto) throws InterruptedException, ExecutionException {
		
		validatorUserFormation.validateCreateUserFormationDTO(dto);
		if(dto.getNinjas() == null || dto.getNinjas().isEmpty()) {
			return null;
		}
		
		UserFormation result = formationService.createUserFormation(dto);
		UserFormationDTO response = null;
		boolean merge = true;
		
		if(merge) {
			//result = formationService.saveUserFormation(result);		
			response = formationService.mergeBonus(result);
		}else {
			response = userFormationMapper.toDTO(formationService.saveUserFormation(result));	
			//result = formationService.saveUserFormation(result);
		}
		
		
		ResponseEntity <UserFormationDTO> responseDTO = null;
		
		if(response != null) {
			responseDTO = new ResponseEntity <>(response,HttpStatus.OK);
		}else {
			responseDTO = new ResponseEntity <>(null,HttpStatus.NO_CONTENT);
		}
		
		return responseDTO;
	}
}
