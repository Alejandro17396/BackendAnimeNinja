package com.alejandro.animeninja.presentation.controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.alejandro.animeninja.bussines.annotation.PageableConstraint;
import com.alejandro.animeninja.bussines.auth.services.JWTService;
import com.alejandro.animeninja.bussines.exceptions.SetException;
import com.alejandro.animeninja.bussines.exceptions.UserException;
import com.alejandro.animeninja.bussines.mappers.NinjaMapper;
import com.alejandro.animeninja.bussines.mappers.SkillAttributeMapper;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.NinjaUserFormation;
import com.alejandro.animeninja.bussines.model.NinjaSkill;
import com.alejandro.animeninja.bussines.model.Pagination;
import com.alejandro.animeninja.bussines.model.SkillType;
import com.alejandro.animeninja.bussines.model.UserAccesories;
import com.alejandro.animeninja.bussines.model.dto.CompareNinjaUserDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateAccesorieSetAttributesDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateAccesorieSetDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateComboNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateNinjaAttributesDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateNinjaEquipmentDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateSetAttributesDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateUserFormationCombosDTO;
import com.alejandro.animeninja.bussines.model.dto.FinalSkillsAttributesDTO;
import com.alejandro.animeninja.bussines.model.dto.FormationNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.FormationsNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaUserFormationDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjasDTO;
import com.alejandro.animeninja.bussines.model.dto.SuccesDTO;
import com.alejandro.animeninja.bussines.model.dto.UserAccesoriesDTO;
import com.alejandro.animeninja.bussines.model.dto.UserFormationDTO;
import com.alejandro.animeninja.bussines.model.dto.UserSetDTO;
import com.alejandro.animeninja.bussines.services.AccesorioServices;
import com.alejandro.animeninja.bussines.services.EquipoServices;
import com.alejandro.animeninja.bussines.services.FormationService;
import com.alejandro.animeninja.bussines.services.JsonMapperObjectsService;
import com.alejandro.animeninja.bussines.services.NinjaService;
import com.alejandro.animeninja.bussines.services.NinjaSkillService;
import com.alejandro.animeninja.bussines.validators.ValidatorNinjaService;
import com.alejandro.animeninja.integration.repositories.NinjaEquipmentRepository;

@RestController
@CrossOrigin
@RequestMapping("/ninjas")
public class NinjasController {

	@Autowired
	private NinjaService ninjaService;
	
	@Autowired
	private EquipoServices equipoServices;

	@Autowired
	private AccesorioServices accesoriesServices;
	
	@Autowired
	private NinjaMapper ninjaMapper;
	
	@Autowired
	private SkillAttributeMapper skillMapper;
	
	@Autowired
	private ValidatorNinjaService validator;

	@Autowired
	private NinjaSkillService skillService;
	
	@Autowired
	private FormationService formationService;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired(required=true)
	private JsonMapperObjectsService jsonMapperService;
	
	
	@GetMapping
	public NinjasDTO getNinjaPaged(Pageable pageable) {

		NinjasDTO response = new NinjasDTO();
		Page <Ninja> list = ninjaService.getAllPaged(pageable);
		response.setNinjas(ninjaMapper.toDtoList(list.getContent()));
		response.setNumber(Long.valueOf(list.getTotalElements()).intValue());
		return response;
		
	}
	
	@GetMapping("/all")
	public NinjasDTO getAllNinjas() {

		NinjasDTO response = new NinjasDTO();
		List <Ninja> list = ninjaService.getAll();
		response.setNinjas(ninjaMapper.toDtoList(list));
		response.setNumber(list.size());
		return response;
		
	}

	@PostMapping("/filter/and")
	public ResponseEntity <Page <NinjaDTO>> getNinjaFiltroAnd(@RequestBody(required = false) CreateComboNinjaDTO attributes,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred,
			@RequestParam(value = "or", required = false, defaultValue = "false") boolean or,
			@RequestParam(value = "awakenings", required = false, defaultValue = "true") boolean awakenings,
			Pageable pageable) {

		validator.validateCreateComboNinjaDTO(attributes);
		
		Page <NinjaDTO> responseDTO = ninjaService.getNinjaFiltroAndNoPaged(attributes, sorted, filtred,awakenings,or,pageable);
		
		ResponseEntity <Page <NinjaDTO>> response = null;
		if(responseDTO.getContent().size() > 0) {
			response = new ResponseEntity <>(responseDTO,HttpStatus.OK);
		}else {
			response = new ResponseEntity <>(responseDTO,HttpStatus.NO_CONTENT);
		}
		
		return response;
	}
	
	@PostMapping("/filter/or")
	public ResponseEntity <Page <NinjaDTO>> getNinjaFiltroOr(@RequestBody(required = false) CreateComboNinjaDTO attributes,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred,
			@Valid @PageableConstraint Pageable pageable) {

		validator.validateCreateComboNinjaDTO(attributes);
		
		Page <NinjaDTO> responseDTO = ninjaService.getNinjaFiltroOr(attributes, sorted, filtred,pageable);
		
		ResponseEntity <Page <NinjaDTO>> response = null;
		if(responseDTO.getContent().size() > 0) {
			response = new ResponseEntity <>(responseDTO,HttpStatus.OK);
		}else {
			response = new ResponseEntity <>(responseDTO,HttpStatus.NO_CONTENT);
		}
		
		return response;
	}
	
	@PostMapping("/createComboFormations")
	public ResponseEntity <FormationsNinjaDTO> getNinjaComboFormations(
			@RequestBody(required = false) CreateComboNinjaDTO dto,
			@RequestParam(value = "merge", required = false, defaultValue = "true") boolean merge,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred,
			@RequestParam(value = "or", required = false, defaultValue = "false") boolean or,
			@RequestParam(value = "awakenings", required = false, defaultValue = "true") boolean awakenings,
			Pageable pageable) {
 
		validator.validateCreateComboNinjaDTO(dto);
		Long ini,fin;
		ini = System.currentTimeMillis();
		ResponseEntity <FormationsNinjaDTO> response = null;
		FormationsNinjaDTO responseDTO = new FormationsNinjaDTO();
		List <FormationNinjaDTO> list = ninjaService.getNinjaComboFormations(dto, 
				merge, sorted, filtred, or, awakenings);
		Pagination <FormationNinjaDTO> pagination =  new Pagination <FormationNinjaDTO> 
		(list,pageable.getPageNumber(),pageable.getPageSize());
		responseDTO.setFormations(pagination.getPagedList());
		responseDTO.setNumFormations(list.size());
		
		if(responseDTO.getNumFormations() > 0) {
			response = new ResponseEntity <>(responseDTO,HttpStatus.OK);
		}else {
			response = new ResponseEntity <>(responseDTO,HttpStatus.NO_CONTENT);
		}

		fin = System.currentTimeMillis();
		System.out.println("Tarde "+(fin-ini));
		return response;
	}	
	
	@PostMapping("/createFormation")
	public FormationNinjaDTO getNinjaComboFormations(@RequestBody HashMap<String,SkillType> request,
			@RequestParam(value = "awakenings", required = false, defaultValue = "true") boolean awakenings
			) throws InterruptedException, ExecutionException{
		/*List <Ninja> ninjas = new ArrayList<>();
		List <NinjaSkill> skills = new ArrayList<>();
		CompletableFuture <?> ninjaCompletables [] = new CompletableFuture<?> [request.size()];
		CompletableFuture <?> skillCompletables [] = new CompletableFuture<?> [request.size()];
		
		int i = 0;
		for(Map.Entry<String,SkillType> entry : request.entrySet()) {
			ninjaCompletables[i] = ninjaService.getNinjaByName(entry.getKey());
			skillCompletables[i] = skillService.findByNinjaAndTypeAsync(entry.getKey(),entry.getValue());
			i++;
		}
		
		for(CompletableFuture <?> completable : ninjaCompletables) {
			Ninja ninja = (Ninja) completable.get();
			if(ninja != null) {
				ninjas.add(ninja);
			}
		}

		for(CompletableFuture <?> completable : skillCompletables) {
			NinjaSkill skill = (NinjaSkill) completable.get();
			if(skill != null) {
				skills.add(skill);
			}
		}
		
		FormationNinjaDTO formation = ninjaService.createFormationWithNinjas(ninjas,awakenings);
		FinalSkillsAttributesDTO finalSkill = new FinalSkillsAttributesDTO();
		finalSkill.setAttributes(skillMapper.toDTOList(skillService.createFinalSkill(skills)));
		finalSkill.setNinjaFormation(formation.getFormationNinjas());
		formation.getFinalSkillsAttributes().add(finalSkill);*/
		
		FormationNinjaDTO formation = formationService.createFormation(request, awakenings);
		return formation;
	}
	
	@Autowired
	private NinjaEquipmentRepository repository1;
	
	@GetMapping("/equipment")
	public List <NinjaUserFormation> getNinjaEquipment() {
		
		List <NinjaUserFormation> list = repository1.findAll();
		return list;
	}
	
	@GetMapping("/findById/{name}")
	public ResponseEntity <NinjaDTO> getNinjaByName(@PathVariable(name="name") String name) {
		
		Ninja ninja = ninjaService.getNinja(name);
		
		ResponseEntity <NinjaDTO> response = null;
		NinjaDTO dto = null;
		if(ninja != null) {
			dto = ninjaMapper.toDTO(ninja);
			response = new ResponseEntity <>(dto,HttpStatus.OK);
		}else {
			response = new ResponseEntity <>(null,HttpStatus.NO_CONTENT);
		}
		
		return response;
	}
	
	@PostMapping("/create")
	public ResponseEntity <NinjaUserFormationDTO> createSet(
			@RequestBody CreateNinjaEquipmentDTO dto,
			@RequestHeader (name="Authorization") String token){
		
		String user = jwtService.getUsername(token);
		if(user == null) {
			throw new UserException("400", "Invalid token", HttpStatus.BAD_REQUEST);
		}
		
		NinjaUserFormation ninja = ninjaService.createNinjaFormationByNameAndUsername(dto, user);
		NinjaUserFormationDTO response = null;
		boolean merge = true;
		if(merge) {
			response = ninjaService.mergeBonus(ninja);
		}else {
			response =  ninjaMapper.toNinjaUserFormationDTO(ninja);
		}
		
		ResponseEntity <NinjaUserFormationDTO> responseDTO = null;
		
		if(response != null) {
			responseDTO = new ResponseEntity <>(response,HttpStatus.OK);
		}else {
			responseDTO = new ResponseEntity <>(null,HttpStatus.NO_CONTENT);
		}
		return responseDTO;
	}
	
	@PostMapping("/calculateNinjaBonuses")
	public ResponseEntity <NinjaUserFormationDTO> calculateNinjaFinalBonuses(
			@RequestBody CreateNinjaEquipmentDTO dto){
		
		NinjaUserFormation ninja = ninjaService.createMockUserNinja(dto);
		NinjaUserFormationDTO response = null;
		response = ninjaService.mergeBonus(ninja);
		ResponseEntity <NinjaUserFormationDTO> responseDTO = null;
		
		if(response != null) {
			responseDTO = new ResponseEntity <>(response,HttpStatus.OK);
		}else {
			responseDTO = new ResponseEntity <>(null,HttpStatus.NO_CONTENT);
		}
		return responseDTO;
	}
	
	@PutMapping("/update")
	public ResponseEntity <NinjaUserFormationDTO> updateNinja(
			@RequestBody CreateNinjaEquipmentDTO dto,
			@RequestHeader (name="Authorization") String token){
		
		String user = jwtService.getUsername(token);
		if(user == null) {
			throw new UserException("400", "Invalid token", HttpStatus.BAD_REQUEST);
		}
		
		NinjaUserFormation accesories = ninjaService.updateNinjaFormationByNameAndUsername(dto, user);
		NinjaUserFormationDTO response = null;
		boolean merge = true;
		if(merge) {
			response = ninjaService.mergeBonus(accesories);
		}else {
			response =  ninjaMapper.toNinjaUserFormationDTO(accesories);
		}
		
		ResponseEntity <NinjaUserFormationDTO> responseDTO = null;
		
		if(response != null) {
			responseDTO = new ResponseEntity <>(response,HttpStatus.OK);
		}else {
			responseDTO = new ResponseEntity <>(null,HttpStatus.NO_CONTENT);
		}
		return responseDTO;
	}
	
	
	@PostMapping("/compareNinjas")
	public ResponseEntity <CompareNinjaUserDTO> comapreNinjas(
			@RequestBody CompareNinjaUserDTO dto,
			@RequestHeader (name="Authorization") String token){
		
		String user = jwtService.getUsername(token);
		if(user == null) {
			throw new UserException("400", "Invalid token", HttpStatus.BAD_REQUEST);
		}
		
		CompareNinjaUserDTO compareNinjas = ninjaService.
				comapreNinjasUser(dto);
		UserSetDTO setAuxLeft = null;
		UserSetDTO setAuxRight = null;
		if(dto.getNinjaLeft() != null) {
			setAuxLeft = dto.getNinjaLeft().getEquipment();
		}
		
		if(dto.getNinjaRight() != null) {
			setAuxRight = dto.getNinjaRight().getEquipment();
		}
		
		equipoServices.compareSetsBonuses(setAuxLeft,setAuxRight);
		
		
		UserAccesoriesDTO setAccesorieAuxLeft = null;
		UserAccesoriesDTO setAccesorieAuxRight = null;
		if(dto.getNinjaLeft() != null) {
			setAccesorieAuxLeft = dto.getNinjaLeft().getAccesories();
		}
		
		if(dto.getNinjaRight() != null) {
			setAccesorieAuxRight = dto.getNinjaRight().getAccesories();
		}
		
		accesoriesServices.compareAccesorieSetBonuses(
				setAccesorieAuxLeft,
				setAccesorieAuxRight);
		
		/*NinjaUserFormationDTO response = null;
		boolean merge = true;
		if(merge) {
			response = ninjaService.mergeBonus(accesories);
		}else {
			response =  ninjaMapper.toNinjaUserFormationDTO(accesories);
		}*/
		
		ResponseEntity <CompareNinjaUserDTO> responseDTO = null;
		
		if(compareNinjas != null) {
			responseDTO = new ResponseEntity <>(compareNinjas,HttpStatus.OK);
		}else {
			responseDTO = new ResponseEntity <>(null,HttpStatus.NO_CONTENT);
		}
		return responseDTO;
	}
	
	
	@GetMapping("/findByUser")
	public ResponseEntity <List<NinjaUserFormationDTO>> getNinjasByUser(@RequestHeader (name="Authorization") String token){
		
		String user = jwtService.getUsername(token);
		
		if(user == null) {
			throw new UserException("400","has no access",HttpStatus.BAD_REQUEST);
		}
		
		List <NinjaUserFormationDTO> response = ninjaService.
				getNinjasByUser(user);
		
		ResponseEntity <List <NinjaUserFormationDTO>> responseDTO = null;
		
		if(response != null) {
			responseDTO = new ResponseEntity <>(response,HttpStatus.OK);
		}else {
			responseDTO = new ResponseEntity <>(null,HttpStatus.NO_CONTENT);
		}
		
		return responseDTO;
	}
	
	@GetMapping("/findByName/{name}")
	public ResponseEntity <NinjaUserFormationDTO> getNinjasByName(@PathVariable String name,@RequestHeader (name="Authorization") String token){
		
		String user = jwtService.getUsername(token);
		
		if(user == null) {
			throw new UserException("400","has no access",HttpStatus.BAD_REQUEST);
		}
		
		NinjaUserFormation response = ninjaService.getNinjaByName(name,user);
		
		ResponseEntity <NinjaUserFormationDTO> responseDTO = null;
		
		if(response != null) {
			responseDTO = new ResponseEntity <>(ninjaMapper.toNinjaUserFormationDTO(response),HttpStatus.OK);
		}else {
			responseDTO = new ResponseEntity <>(null,HttpStatus.NO_CONTENT);
		}
		
		return responseDTO;
	}
	
	
	@DeleteMapping("/deleteByName/{name}")
	public ResponseEntity <SuccesDTO> deleteNinjasByName(@PathVariable String name,@RequestHeader (name="Authorization") String token){
		
		String user = jwtService.getUsername(token);
		
		if(user == null) {
			throw new UserException("400","has no access",HttpStatus.BAD_REQUEST);
		}
		
		boolean response = ninjaService.deleteNinjaByName(name,user);
		
		ResponseEntity <SuccesDTO> responseDTO = null;
		SuccesDTO dto = new SuccesDTO();
		
		if(response ) {
			dto.setMessage(String.format("Ninja %s deleted succesfully", name));
			responseDTO = new ResponseEntity <>(dto,HttpStatus.OK);
		}else {
			dto.setMessage(String.format("Ninja %s cant be deleted", name));
			responseDTO = new ResponseEntity <>(dto,HttpStatus.NO_CONTENT);
		}
		
		return responseDTO;
	}
	
	
	@GetMapping("/generateNinjaUserCombos")
	public ResponseEntity <List <NinjaUserFormationDTO>> createFormationCombosForUser(
			@RequestBody CreateUserFormationCombosDTO dto
			/*@RequestHeader (name="Authorization") String token*/) throws InterruptedException, ExecutionException {
		
		ninjaService.getAll();
		
		/*List <NinjaUserFormationDTO> response = ninjaService.crea
				formationService.createUserComboFormation(dto, null);
		
		ResponseEntity <List <NinjaUserFormationDTO>> responseDTO = null;
		
		if(response != null) {
			responseDTO = new ResponseEntity <>(response,HttpStatus.OK);
		}else {
			responseDTO = new ResponseEntity <>(null,HttpStatus.NO_CONTENT);
		}
		
		return responseDTO;*/
		
		return null;
	}
	
	@PostMapping(value="/ninja/create",consumes = {MediaType.APPLICATION_JSON_VALUE, 
			 MediaType.MULTIPART_FORM_DATA_VALUE})
	@Transactional
	public ResponseEntity<Ninja> createSet(//@RequestBody CreateNinjaAttributesDTO dto){
			@RequestPart("body") String json,
			@RequestPart(value ="files",required=false) List<MultipartFile> files) throws IOException{
		
		CreateNinjaAttributesDTO dto = jsonMapperService.jsonToCreateNinjaAttributesDTO(json);
		String name = dto.getNinja().getName();
		Ninja result = ninjaService.getNinja(name);
		if(result != null) {
			throw new SetException("400","There is already an equipment named " + name,HttpStatus.BAD_REQUEST);
		}
		result = ninjaService.createNewNinja(dto,files);
		ResponseEntity <Ninja> responseDTO = null;
		
		if(result != null) {
			responseDTO = new ResponseEntity <>(result,HttpStatus.OK);
		}
		
		return responseDTO;
	}
	
	/*@PostMapping("/ninja/create")
	@Transactional
	public ResponseEntity<Ninja> createSet(@RequestBody CreateNinjaAttributesDTO dto){

		
		String name = dto.getNinja().getName();
		Ninja result = ninjaService.getNinja(name);
		if(result != null) {
			throw new SetException("400","There is already an equipment named " + name,HttpStatus.BAD_REQUEST);
		}
		result = ninjaService.createNewNinja(dto);
		ResponseEntity <Ninja> responseDTO = null;
		
		if(result != null) {
			responseDTO = new ResponseEntity <>(result,HttpStatus.OK);
		}
		
		return responseDTO;
	}*/
	
	@PutMapping(value="/ninja/update")
	@Transactional
	public ResponseEntity<Ninja> updateSet(//@RequestBody CreateNinjaAttributesDTO dto){
			@RequestPart("body") String json,
			@RequestPart(value ="files",required=false) List<MultipartFile> files) throws IOException{
		
		CreateNinjaAttributesDTO dto = jsonMapperService.jsonToCreateNinjaAttributesDTO(json);
		String name = dto.getNinja().getName();
		Ninja result = ninjaService.getNinja(name);
		if(result == null) {
			throw new SetException("400","There is no equipment named " + name,HttpStatus.BAD_REQUEST);
		}
		result =  ninjaService.updateNinja(dto,files);
		ResponseEntity <Ninja> responseDTO = null;
		
		if(result != null) {
			responseDTO = new ResponseEntity <>(result,HttpStatus.OK);
		}
		
		return responseDTO;
	}
	
	/*@PutMapping("/ninja/update")
	@Transactional
	public ResponseEntity<Ninja> updateSet(@RequestBody CreateNinjaAttributesDTO dto){

		String name = dto.getNinja().getName();
		Ninja result = ninjaService.getNinja(name);
		if(result == null) {
			throw new SetException("400","There is no equipment named " + name,HttpStatus.BAD_REQUEST);
		}
		result =  ninjaService.updateNinja(dto);
		ResponseEntity <Ninja> responseDTO = null;
		
		if(result != null) {
			responseDTO = new ResponseEntity <>(result,HttpStatus.OK);
		}
		
		return responseDTO;
	}*/
	
	@DeleteMapping("/ninja/delete/{name}")

	public ResponseEntity<SuccesDTO> deleteSet(@PathVariable String name){

		Boolean result = ninjaService.deleteNinja(name);
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
	
	public void showHeapMemory() {
		 int dataSize = 1024 * 1024;
		 Runtime runtime = Runtime.getRuntime();
		 System.out.println ("Memoria máxima: " + runtime.maxMemory() / dataSize + "MB");
		 System.out.println ("Memoria total: " + runtime.totalMemory() / dataSize + "MB");
		 System.out.println ("Memoria libre: " + runtime.freeMemory() / dataSize + "MB");
		 System.out.println ("Memoria usada: " + (runtime.totalMemory() - runtime.freeMemory()) / dataSize + "MB");
	}
	
	

}
