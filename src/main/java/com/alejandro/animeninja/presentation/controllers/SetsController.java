package com.alejandro.animeninja.presentation.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.alejandro.animeninja.bussines.exceptions.SetException;
import com.alejandro.animeninja.bussines.exceptions.UserException;
import com.alejandro.animeninja.bussines.mappers.SetMapper;
import com.alejandro.animeninja.bussines.model.Constantes;
import com.alejandro.animeninja.bussines.model.CreateComboSet;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.NinjaUserFormation;
import com.alejandro.animeninja.bussines.model.Pagination;
import com.alejandro.animeninja.bussines.model.UserFormation;
import com.alejandro.animeninja.bussines.model.UserSet;
import com.alejandro.animeninja.bussines.model.dto.CreateSetDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaUserFormationDTO;
import com.alejandro.animeninja.bussines.model.dto.SetDTO;
import com.alejandro.animeninja.bussines.model.dto.SetsDTO;
import com.alejandro.animeninja.bussines.model.dto.SuccesDTO;
import com.alejandro.animeninja.bussines.model.dto.UserFormationDTO;
import com.alejandro.animeninja.bussines.model.dto.UserSetDTO;
import com.alejandro.animeninja.bussines.services.EquipoServices;
import com.alejandro.animeninja.bussines.validators.ValidatorNinjaService;
import com.alejandro.animeninja.integration.repositories.NinjaEquipmentRepository;
import com.alejandro.animeninja.integration.repositories.UserFormationRepository;

@RestController

@RequestMapping("/sets")
public class SetsController {

	@Autowired
	private EquipoServices equipoServices;
	
	@Autowired
	private SetMapper setMapper;
	
	@Autowired
	private ValidatorNinjaService validator;
	
	@Autowired
	private JWTService jwtService;
	
	@GetMapping
	public ResponseEntity <Page <SetDTO>> getAll(Pageable pageable) { 
		
		Page <SetDTO> responseDTO = equipoServices.getAllPage(pageable);
		ResponseEntity <Page <SetDTO>> response = null;
		
		if(responseDTO.getContent().size() > 0) {
			response = new ResponseEntity <>(responseDTO,HttpStatus.OK);
		}else {
			response = new ResponseEntity <>(responseDTO,HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/filterSetsByAttributes")
	public ResponseEntity <Page <SetDTO>> getSetsByAttributesSpecification(@RequestBody(required = false) CreateComboSet attributes,
			@RequestParam(value = "merge", required = false, defaultValue = "true") boolean merge,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred,
			Pageable pageable) {
		
		validator.validateCreateComboSet(attributes);
		
		Page <SetDTO> responseDTO = equipoServices.getSetsByAttributes2(attributes,merge,filtred,sorted,pageable);
		ResponseEntity <Page <SetDTO>> response = null;
		
		if(responseDTO.getContent().size() > 0) {
			response = new ResponseEntity <>(responseDTO,HttpStatus.OK);
		}else {
			response = new ResponseEntity <>(responseDTO,HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/getSet/{nombre}")
	public ResponseEntity <SetDTO> getSetById(@PathVariable String nombre) {
		ResponseEntity <SetDTO> response = null;
		SetDTO responseDTO = setMapper.toDTO(equipoServices.getByNombre(nombre));
		
		if(responseDTO != null) {
			response = new ResponseEntity <>(responseDTO,HttpStatus.OK);
		}else {
			response = new ResponseEntity <>(responseDTO,HttpStatus.NO_CONTENT);
		}
		return response;
	}

	
	@PostMapping("/CombinacionesBonusTotal")
	public ResponseEntity <SetsDTO> CombineSetsByAttributesTotal(@RequestBody(required = false) CreateComboSet attributes,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred,
			Pageable pageable) {

		Long ini,fin;
		ini = System.currentTimeMillis();
		validator.validateCreateComboSet(attributes);
		Pagination <SetDTO> pagination =  new Pagination <SetDTO> (equipoServices.generateCombinationSetsByBonus(attributes,
				sorted, filtred, null, pageable),pageable.getPageNumber(),pageable.getPageSize());
		SetsDTO responseDTO = new SetsDTO();
		responseDTO.setSets(pagination.getPagedList());
		responseDTO.setNumber(pagination.getPagedList().size());
		ResponseEntity <SetsDTO> response = null;
		
		if(responseDTO.getSets().size() > 0) {
			response = new ResponseEntity <>(responseDTO,HttpStatus.OK);
		}else {
			response = new ResponseEntity <>(responseDTO,HttpStatus.NO_CONTENT);
		}
		
		fin = System.currentTimeMillis();
		System.out.println("Tarde "+(fin-ini));
		return response;
		
	}
	
	
	@PostMapping("/create")
	public ResponseEntity <UserSetDTO> createSet(
			@RequestBody CreateSetDTO dto,
			@RequestHeader (name="Authorization") String token){
		
		String user = jwtService.getUsername(token);
		
		if(user == null) {
			throw new UserException("400", "Invalid token", HttpStatus.BAD_REQUEST);
		}
		
		ResponseEntity <List <UserSetDTO>> sets = getSetsByUser(token);
		if(sets.getBody().size() >= Constantes.MAX_SETS) {
			throw new SetException("400","you cant create more sets update or delete 1", HttpStatus.FORBIDDEN);
		}
		
		UserSet set = equipoServices.createSetByName(dto,user);
		UserSetDTO response = null;
		boolean merge = true;
		if(merge) {
			response = equipoServices.mergeBonus(set);
		}else {
			response = setMapper.toUserSetDTO(set);
		}
		
		ResponseEntity <UserSetDTO> responseDTO = null;
		
		if(response != null) {
			responseDTO = new ResponseEntity <>(response,HttpStatus.OK);
		}else {
			responseDTO = new ResponseEntity <>(null,HttpStatus.NO_CONTENT);
		}
		
		return responseDTO;
	}
	
	@PutMapping("/update")
	public ResponseEntity <UserSetDTO> updateSet(
			@RequestBody CreateSetDTO dto,
			@RequestHeader (name="Authorization") String token){
		
		String user = jwtService.getUsername(token);
		
		if(user == null) {
			throw new UserException("400", "Invalid token", HttpStatus.BAD_REQUEST);
		}
		
		UserSet set = equipoServices.UpdateSetByName(dto, user);
		UserSetDTO response = null;
		boolean merge = true;
		if(merge) {
			response = equipoServices.mergeBonus(set);
		}else {
			response = setMapper.toUserSetDTO(set);
		}
		
		ResponseEntity <UserSetDTO> responseDTO = null;
		
		if(response != null) {
			responseDTO = new ResponseEntity <>(response,HttpStatus.OK);
		}else {
			responseDTO = new ResponseEntity <>(null,HttpStatus.NO_CONTENT);
		}
		
		return responseDTO;
	}
	
	@GetMapping("/chatgpt")
	public ResponseEntity <SetsDTO> createSet(@RequestBody(required = false) CreateComboSet attributes,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred,
			Pageable pageable){
		Long ini,fin;
		ini = System.currentTimeMillis();
		validator.validateCreateComboSet(attributes);
		Pagination <SetDTO> pagination =  new Pagination <SetDTO> (equipoServices.generateCombos(attributes,
				sorted, filtred, null, pageable),pageable.getPageNumber(),pageable.getPageSize());
		SetsDTO responseDTO = new SetsDTO();
		responseDTO.setSets(pagination.getPagedList());
		responseDTO.setNumber(pagination.getPagedList().size());
		ResponseEntity <SetsDTO> response = null;
		
		if(responseDTO.getSets().size() > 0) {
			response = new ResponseEntity <>(responseDTO,HttpStatus.OK);
		}else {
			response = new ResponseEntity <>(responseDTO,HttpStatus.NO_CONTENT);
		}
		
		fin = System.currentTimeMillis();
		System.out.println("Tarde "+(fin-ini));
		return response;
	}
	
	@GetMapping("/findByUser")
	public ResponseEntity <List<UserSetDTO>> getSetsByUser(@RequestHeader (name="Authorization") String token){
		
		String user = jwtService.getUsername(token);
		
		if(user == null) {
			throw new UserException("400","has no access",HttpStatus.BAD_REQUEST);
		}
		
		List <UserSetDTO> response = equipoServices.getNinjasByUser(user);
		
		ResponseEntity <List <UserSetDTO>> responseDTO = null;
		
		if(response != null) {
			responseDTO = new ResponseEntity <>(response,HttpStatus.OK);
		}else {
			responseDTO = new ResponseEntity <>(null,HttpStatus.NO_CONTENT);
		}
		
		return responseDTO;
	}
	
	
	@GetMapping("/findBy/{name}")
	public ResponseEntity <UserSetDTO> getSetByName(@PathVariable String name, @RequestHeader (name="Authorization") String token){
		
		String user = jwtService.getUsername(token);
		
		if(user == null) {
			throw new UserException("400","has no access",HttpStatus.BAD_REQUEST);
		}
		
		UserSet response = equipoServices.getUserSetByName(user, name);
		
		ResponseEntity <UserSetDTO> responseDTO = null;
		
		if(response != null) {
			responseDTO = new ResponseEntity <>(setMapper.toUserSetDTO(response),HttpStatus.OK);
		}else {
			responseDTO = new ResponseEntity <>(null,HttpStatus.NO_CONTENT);
		}
		
		return responseDTO;
	}
	
	/*@DeleteMapping("/deleteByName/{name}")
	public ResponseEntity <String> deleteSetByName(@PathVariable String name, @RequestHeader (name="Authorization") String token){
		
		String user = jwtService.getUsername(token);
		
		if(user == null) {
			throw new UserException("400","has no access",HttpStatus.BAD_REQUEST);
		}
		
		boolean response = equipoServices.deleteUserSetByName(name,user);
		
		ResponseEntity <String> responseDTO = null;
		
		if(response ) {
			responseDTO = new ResponseEntity <>(String.format("set %s deleted succesfully", name),HttpStatus.OK);
		}else {
			responseDTO = new ResponseEntity <>(null,HttpStatus.NO_CONTENT);
		}
		
		return responseDTO;
	} */
	
	@DeleteMapping("/deleteByName/{name}")
	public ResponseEntity <SuccesDTO> deleteSetByName(@PathVariable String name, @RequestHeader (name="Authorization") String token){
		
		String user = jwtService.getUsername(token);
		
		if(user == null) {
			throw new UserException("400","has no access",HttpStatus.BAD_REQUEST);
		}
		
		boolean response = equipoServices.deleteUserSetByName(name,user);
		
		ResponseEntity <SuccesDTO> responseDTO = null;
		
		if(response) {
			SuccesDTO respo = new SuccesDTO();
			respo.setMessage(String.format("set %s deleted succesfully", name));
			responseDTO = new ResponseEntity <>(respo,HttpStatus.OK);
		}else {
			SuccesDTO respo = new SuccesDTO();
			respo.setMessage(String.format("set %s cant be deleted", name));
			responseDTO = new ResponseEntity <>(respo,HttpStatus.NO_CONTENT);
		}
		
		return responseDTO;
	}
	
	
	
	/*@Autowired
	private NinjaEquipmentRepository repository1;
	
	@Autowired
	private UserFormationRepository repository;
	
	@GetMapping("/equipment")
	public NinjaUserFormation getNinjaEquipment() {
		
		List <NinjaUserFormation> list = repository1.findAll();
		return list.get(0);
	}
	
	
	
	
	@GetMapping("/equipmentformation")
	public UserFormation getNinjaEquipment2() {
		
		List <UserFormation> list = repository.findAll();
		return list.get(0);
	}
	
	@GetMapping("/createSet/{nombre}")
	public UserFormation getNinjaEquipment3(@PathVariable String nombre) {
		
		List <UserFormation> list = repository.findAll();
		equipoServices.createSet(nombre);
		return list.get(0);
	}*/

}
