package com.alejandro.animeninja.presentation.controllers;



import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
import com.alejandro.animeninja.bussines.model.dto.CreateSetAttributesDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateSetDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaUserFormationDTO;
import com.alejandro.animeninja.bussines.model.dto.SetAccesorioDTO;
import com.alejandro.animeninja.bussines.model.dto.SetDTO;
import com.alejandro.animeninja.bussines.model.dto.SetsDTO;
import com.alejandro.animeninja.bussines.model.dto.SuccesDTO;
import com.alejandro.animeninja.bussines.model.dto.UserFormationDTO;
import com.alejandro.animeninja.bussines.model.dto.UserSetDTO;
import com.alejandro.animeninja.bussines.services.EquipoServices;
import com.alejandro.animeninja.bussines.services.JsonMapperObjectsService;
import com.alejandro.animeninja.bussines.validators.ValidatorNinjaService;
import com.alejandro.animeninja.integration.repositories.NinjaEquipmentRepository;
import com.alejandro.animeninja.integration.repositories.UserFormationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	@Autowired(required=true)
	private JsonMapperObjectsService jsonMapperService;
	 
	@GetMapping
	public ResponseEntity <Page <SetDTO>> getAllPaged(	Pageable pageable,
				@RequestParam(value = "name", required = false) String name) { 
		
		Page<SetDTO> responseDTO;
		if(name != null && !name.isEmpty()) {
			responseDTO = equipoServices.getAllPageNameContains(pageable,name);
		}else {
			responseDTO = equipoServices.getAllPage(pageable);
		}
		
		ResponseEntity <Page <SetDTO>> response  = new ResponseEntity <>(responseDTO,HttpStatus.OK);

		return response;
	}
	
	@GetMapping("/all")
	public ResponseEntity <Page <SetDTO>> getAll(Pageable pageable) { 
		
		List <SetDTO> responseDTO = setMapper.toDtoList(equipoServices.getAll());
		ResponseEntity <Page <SetDTO>> response = null;
		
		Page <SetDTO> p =new PageImpl<SetDTO>(responseDTO,pageable,responseDTO.size());
		
		if(responseDTO.size() > 0) {
			response = new ResponseEntity <>(p,HttpStatus.OK);
		}else {
			response = new ResponseEntity <>(p,HttpStatus.NO_CONTENT);
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
		if(sets.getBody()!= null && sets.getBody().size() >= Constantes.MAX_SETS) {
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
	
	@PostMapping("/chatgpt")
	public ResponseEntity <SetsDTO> createSet(@RequestBody(required = false) CreateComboSet attributes,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred,
			@RequestParam(value = "name", required = false) String name,
			Pageable pageable){
		Long ini,fin;
		ini = System.currentTimeMillis();
		validator.validateCreateComboSet(attributes);
		List<SetDTO> result =equipoServices.generateCombos(attributes,
				sorted, filtred, null, pageable);
		
		List <SetDTO> lista = new ArrayList<>();
		
		if(name != null && !name.isEmpty()) {
			for(SetDTO set : result) {
				if(set.getNombre().contains(name)) {
					lista.add(set);
				}
			}
		}else {
			lista = result;
		}
		
		Pagination <SetDTO> pagination =  new Pagination <SetDTO> (lista,pageable.getPageNumber(),pageable.getPageSize());
		SetsDTO responseDTO = new SetsDTO();
		responseDTO.setSets(pagination.getPagedList());
		responseDTO.setNumber(lista.size());
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
	
	@PostMapping("/transform")
	public ResponseEntity<SetDTO> transformUserSetToNormalSet(@RequestBody CreateSetDTO dto, 
			@RequestHeader (name="Authorization") String token){
		String user = jwtService.getUsername(token);
		
		if(user == null) {
			throw new UserException("400","has no access",HttpStatus.BAD_REQUEST);
		}
		
		Equipo set = equipoServices.createSet(dto.getEquipment(), dto.getSetName());
		set.setNombre(dto.getSetName());
		SetDTO result = setMapper.toDTO(equipoServices.mergeSetBonuses(set));
		ResponseEntity <SetDTO> responseDTO = null;
		
		if(result != null) {
			responseDTO = new ResponseEntity <>(result,HttpStatus.OK);
		}
		
		return responseDTO;
	}
	
	@PostMapping(value="/equipment/create", 
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	@Transactional
	public ResponseEntity<Equipo> createSet(
			@RequestPart("body") String json,
			@RequestPart(value ="files",required=false) List<MultipartFile> files){

		CreateSetAttributesDTO dto = jsonMapperService.jsonToCreateSetAttributeDTO(json);
		
		String name = dto.getSet().getNombre();
		Equipo result = equipoServices.getByNombre(name);
		if(result != null) {
			throw new SetException("400","There is already an equipment named " + name,HttpStatus.BAD_REQUEST);
		}
		result = equipoServices.createNewEquipment(dto,files);
		//Equipo result = new Equipo();
		ResponseEntity <Equipo> responseDTO = null;
		
		if(result != null) {
			responseDTO = new ResponseEntity <>(result,HttpStatus.OK);
		}
		
		return responseDTO;
	}
	
	/*@PostMapping("/equipment/create2")
	@Transactional
	public ResponseEntity<Equipo> createSet2(@RequestBody CreateSetAttributesDTO dto){

		
		String name = dto.getSet().getNombre();
		Equipo result = equipoServices.getByNombre(name);
		if(result != null) {
			throw new SetException("400","There is already an equipment named " + name,HttpStatus.BAD_REQUEST);
		}
		result = equipoServices.createNewEquipment(dto);
		ResponseEntity <Equipo> responseDTO = null;
		
		if(result != null) {
			responseDTO = new ResponseEntity <>(result,HttpStatus.OK);
		}
		
		return responseDTO;
	}*/
	
	/*@PostMapping(value="/equipment/create", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	@Transactional
	public ResponseEntity<Equipo> createSet(
			@RequestPart("body") String json,
			@RequestPart("files") List<MultipartFile> files){*/
	
	@PutMapping(value="/equipment/update",
			 consumes = {MediaType.APPLICATION_JSON_VALUE, 
					 MediaType.MULTIPART_FORM_DATA_VALUE})
	@Transactional
	public ResponseEntity<SetDTO> updateSet(
			@RequestPart("body") String json,
			@RequestPart(value ="files",required=false) List<MultipartFile> files){

		CreateSetAttributesDTO dto = jsonMapperService.jsonToCreateSetAttributeDTO(json);
		String name = dto.getSet().getNombre();
		Equipo result = equipoServices.getByNombre(name);
		if(result == null) {
			throw new SetException("400","There is no equipment named " + name,HttpStatus.BAD_REQUEST);
		}
		result = equipoServices.updateEquipment(dto,files);
		ResponseEntity <SetDTO> responseDTO = null;
		
		if(result != null) {
			responseDTO = new ResponseEntity <>(setMapper.toDTO(result),HttpStatus.OK);
		}
		
		return responseDTO;
	}
	
	/*@PutMapping("/equipment/update")
	@Transactional
	public ResponseEntity<Equipo> updateSet(@RequestBody CreateSetAttributesDTO dto){

		String name = dto.getSet().getNombre();
		Equipo result = equipoServices.getByNombre(name);
		if(result == null) {
			throw new SetException("400","There is no equipment named " + name,HttpStatus.BAD_REQUEST);
		}
		result = equipoServices.updateEquipment(dto);
		ResponseEntity <Equipo> responseDTO = null;
		
		if(result != null) {
			responseDTO = new ResponseEntity <>(result,HttpStatus.OK);
		}
		
		return responseDTO;
	}*/
	
	@DeleteMapping("/equipment/delete/{name}")

	public ResponseEntity<SuccesDTO> deleteSet(@PathVariable String name){

		Boolean result = equipoServices.deleteSet(name);
		ResponseEntity <SuccesDTO> responseDTO = null;
		
		if(result) {
			SuccesDTO response = new SuccesDTO();
			response.setMessage(String.format("equipoment %s deleted succesfully", name));
			responseDTO = new ResponseEntity <>(response,HttpStatus.OK);
		}else {
			SuccesDTO response = new SuccesDTO();
			response.setMessage(String.format("equipoment %s doesnt exist", name));
			responseDTO = new ResponseEntity <>(response,HttpStatus.OK);
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
