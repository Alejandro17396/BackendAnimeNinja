package com.alejandro.animeninja.presentation.controllers;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.auth.services.JWTService;
import com.alejandro.animeninja.bussines.exceptions.UserException;
import com.alejandro.animeninja.bussines.exceptions.UserFormationException;
import com.alejandro.animeninja.bussines.mappers.UserFormationMapper;
import com.alejandro.animeninja.bussines.model.Constantes;
import com.alejandro.animeninja.bussines.model.UserFormation;
import com.alejandro.animeninja.bussines.model.dto.CompareFormationsDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateComboNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateUserFormationCombosDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateUserFormationDTO;
import com.alejandro.animeninja.bussines.model.dto.FormationsNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.SuccesDTO;
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
	
	@Autowired
	private JWTService jwtService;
	
	@GetMapping("/find/{id}")
	public ResponseEntity <UserFormationDTO> findFormation(@PathVariable Long id) throws InterruptedException, ExecutionException {

		UserFormationDTO result = userFormationMapper.toDTO(formationService.getUserFormationById(id));
		ResponseEntity <UserFormationDTO> responseDTO = null;
		formationService.setNinjasPosition(result);
		
		if(result != null) {
			responseDTO = new ResponseEntity <>(result,HttpStatus.OK);
		}else {
			responseDTO = new ResponseEntity <>(null,HttpStatus.NO_CONTENT);
		}
		
		return responseDTO;
	}
	
	@PutMapping("/update")
	public ResponseEntity <UserFormationDTO> updateFormation(
			@RequestBody CreateUserFormationDTO dto,
			@RequestHeader (name="Authorization") String token) throws InterruptedException, ExecutionException {
		
		validatorUserFormation.validateCreateUserFormationDTO(dto);
		if(dto.getNinjas() == null || dto.getNinjas().isEmpty()) {
			return null;
		}
		
		//UserFormation result = formationService.createUserFormation(dto,jwtService.getUsername(token));
		String user = jwtService.getUsername(token);
		
		if(user == null) {
			throw new UserException("400","has no access",HttpStatus.BAD_REQUEST);
		}
		
		UserFormation result = formationService.updateUserFormation(dto,user);
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
	
	@PostMapping("/compareFormations")
	public ResponseEntity <CompareFormationsDTO> getNinjaComboFormations(
			@RequestBody(required = false) CompareFormationsDTO dto,
			@RequestParam(value = "skills", required = false, defaultValue = "true") boolean merge,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred,
			@RequestParam(value = "or", required = false, defaultValue = "true") boolean or,
			@RequestParam(value = "awakenings", required = false, defaultValue = "true") boolean awakenings,
			Pageable pageable) {
		
		
		//formationService.getFormationsByUser(user);
		CompareFormationsDTO result = formationService.compareFormations(dto);
		ResponseEntity <CompareFormationsDTO> responseDTO = null;
		
		if(result != null) {
			responseDTO = new ResponseEntity <>(result,HttpStatus.OK);
		}else {
			responseDTO = new ResponseEntity <>(null,HttpStatus.NO_CONTENT);
		}
		
		return responseDTO;
	}
	
	@PostMapping("/create")
	public ResponseEntity <UserFormationDTO> createFormation(
			@RequestBody CreateUserFormationDTO dto,
			@RequestHeader (name="Authorization") String token) throws InterruptedException, ExecutionException {
		
		validatorUserFormation.validateCreateUserFormationDTO(dto);
		if(dto.getNinjas() == null || dto.getNinjas().isEmpty()) {
			return null;
		}
		
		//UserFormation result = formationService.createUserFormation(dto,jwtService.getUsername(token));
		String user = jwtService.getUsername(token);
		
		if(user == null) {
			throw new UserException("400","has no access",HttpStatus.BAD_REQUEST);
		}
		
		List <UserFormationDTO> formations = formationService.getFormationsByUser(user);
		if(formations.size() >= Constantes.MAX_SETS) {
			throw new UserFormationException("400", "you cant have more formations", HttpStatus.BAD_REQUEST);
		}
		
		
		UserFormation result = formationService.createUserFormation(dto,user);
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
	
	@GetMapping("/chatgpt")
	public ResponseEntity <List <UserFormationDTO>> createFormationCombosForUser(
			@RequestBody CreateUserFormationCombosDTO dto
			/*@RequestHeader (name="Authorization") String token*/) throws InterruptedException, ExecutionException {
		
		
		List <UserFormationDTO> response =formationService.createUserComboFormation(dto, null);
		
		ResponseEntity <List <UserFormationDTO>> responseDTO = null;
		
		if(response != null) {
			responseDTO = new ResponseEntity <>(response,HttpStatus.OK);
		}else {
			responseDTO = new ResponseEntity <>(null,HttpStatus.NO_CONTENT);
		}
		
		return responseDTO;
	}
	
	@GetMapping("/findByUser")
	public ResponseEntity <List <UserFormationDTO>> getFormationsByUser(@RequestHeader (name="Authorization") String token){
		
		String user = jwtService.getUsername(token);
		if(user == null) {
			throw new UserException("400","has no access",HttpStatus.BAD_REQUEST);
		}
		
		List <UserFormationDTO> response = formationService.getFormationsByUser(user);
		
		for(UserFormationDTO formation : response) {
			formationService.setNinjasPosition(formation);
		}
		
		ResponseEntity <List <UserFormationDTO>> responseDTO = null;
		
		if(response != null) {
			responseDTO = new ResponseEntity <>(response,HttpStatus.OK);
		}else {
			responseDTO = new ResponseEntity <>(null,HttpStatus.NO_CONTENT);
		}
		
		return responseDTO;
	}
	
	@GetMapping("/findByName/{name}")
	public ResponseEntity <UserFormationDTO> getFormationByName(@PathVariable String name,@RequestHeader (name="Authorization") String token){
		
		String user = jwtService.getUsername(token);
		if(user == null) {
			throw new UserException("400","has no access",HttpStatus.BAD_REQUEST);
		}
		
		UserFormation response = formationService.getUserFormationByName(name, user);
		
		ResponseEntity <UserFormationDTO> responseDTO = null;
		
		if(response != null) {
			responseDTO = new ResponseEntity <>(userFormationMapper.toDTO(response),HttpStatus.OK);
		}else {
			responseDTO = new ResponseEntity <>(null,HttpStatus.NO_CONTENT);
		}
		
		return responseDTO;
	}
	
	@DeleteMapping("/deleteByName/{name}")
	public ResponseEntity <SuccesDTO> deleteFormationByName(@PathVariable String name,@RequestHeader (name="Authorization") String token){
		
		String user = jwtService.getUsername(token);
		if(user == null) {
			throw new UserException("400","has no access",HttpStatus.BAD_REQUEST);
		}
		
		boolean response = formationService.deleteUserFormationByName(name, user);
		
		ResponseEntity <SuccesDTO> responseDTO = null;
		SuccesDTO dto = new SuccesDTO();
		
		if(response) {
			dto.setMessage(String.format("formation %s deleted succesfully",name));
			responseDTO = new ResponseEntity <>(dto,HttpStatus.OK);
		}else {
			dto.setMessage(String.format("formation %s cant be deleted",name));
			responseDTO = new ResponseEntity <>(dto,HttpStatus.NO_CONTENT);
		}
		
		return responseDTO;
	}
}
