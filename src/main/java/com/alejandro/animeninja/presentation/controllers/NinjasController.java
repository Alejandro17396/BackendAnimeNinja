package com.alejandro.animeninja.presentation.controllers;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.hibernate.mapping.Collection;
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
import com.alejandro.animeninja.bussines.mappers.NinjaSkillMapper;
import com.alejandro.animeninja.bussines.mappers.SkillAttributeMapper;
import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.NinjaSkill;
import com.alejandro.animeninja.bussines.model.Pagination;
import com.alejandro.animeninja.bussines.model.SkillType;
import com.alejandro.animeninja.bussines.model.dto.AttackSkillDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateComboNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.FinalSkillsAttributesDTO;
import com.alejandro.animeninja.bussines.model.dto.FormationNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.FormationsNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjasDTO;
import com.alejandro.animeninja.bussines.services.NinjaService;
import com.alejandro.animeninja.bussines.services.NinjaSkillService;
import com.alejandro.animeninja.bussines.validators.ValidatorNinjaService;

@RestController
@CrossOrigin
@RequestMapping("/ninjas")
public class NinjasController {

	@Autowired
	private NinjaService ninjaService;

	@Autowired
	private NinjaMapper ninjaMapper;
	
	@Autowired
	private SkillAttributeMapper skillMapper;
	
	@Autowired
	private ValidatorNinjaService validator;

	@Autowired
	private NinjaSkillService skillService;
	
	
	@GetMapping
	public NinjasDTO getNinjaPaged(Pageable pageable) {

		NinjasDTO response = new NinjasDTO();
		response.setNinjas(ninjaMapper.toDtoList(ninjaService.getAllPaged(pageable).getContent()));
		response.setNumber(response.getNinjas().size());
		return response;
		
	}

	@GetMapping("/filter/and")
	public ResponseEntity <Page <NinjaDTO>> getNinjaFiltroAnd(@RequestBody(required = false) CreateComboNinjaDTO attributes,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
			Pageable pageable) {

		validator.validateCreateComboNinjaDTO(attributes);
		
		Page <NinjaDTO> responseDTO = ninjaService.getNinjaFiltroAnd(attributes, sorted, filtred,pageable);
		
		ResponseEntity <Page <NinjaDTO>> response = null;
		if(responseDTO.getContent().size() > 0) {
			response = new ResponseEntity <>(responseDTO,HttpStatus.OK);
		}else {
			response = new ResponseEntity <>(responseDTO,HttpStatus.NO_CONTENT);
		}
		
		return response;
	}
	
	@GetMapping("/filter/or")
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
	
	@GetMapping("/createComboFormations")
	public ResponseEntity <FormationsNinjaDTO> getNinjaComboFormations(
			@RequestBody(required = false) CreateComboNinjaDTO externFilter,
			@RequestParam(value = "merge", required = false, defaultValue = "true") boolean merge,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred,
			@RequestParam(value = "or", required = false, defaultValue = "true") boolean or,
			@RequestParam(value = "awakenings", required = false, defaultValue = "true") boolean awakenings,
			Pageable pageable) {
 
		validator.validateCreateComboNinjaDTO(externFilter);
		
		ResponseEntity <FormationsNinjaDTO> response = null;
		FormationsNinjaDTO responseDTO = new FormationsNinjaDTO();
		List <FormationNinjaDTO> list =ninjaService.getNinjaComboFormations(externFilter, merge, sorted, filtred, or, awakenings);
		Pagination <FormationNinjaDTO> pagination =  new Pagination <FormationNinjaDTO> 
		(list,pageable.getPageNumber(),pageable.getPageSize());
		responseDTO.setFormations(pagination.getPagedList());
		responseDTO.setNumFormations(responseDTO.getFormations().size());
		
		 
		if(responseDTO.getNumFormations() > 0) {
			response = new ResponseEntity <>(responseDTO,HttpStatus.OK);
		}else {
			response = new ResponseEntity <>(responseDTO,HttpStatus.NO_CONTENT);
		}

		return response;
	}
	
	
	@GetMapping("/createFormation")
	public String getNinjaComboFormations(@RequestBody AttackSkillDTO request) throws InterruptedException, ExecutionException{
		List <Ninja> ninjas = new ArrayList<>();
		
		validator.validateAttackSkillDTO(request);
		CompletableFuture <?> ninjaCompletables [] = new CompletableFuture<?> [request.getKeys().size()];
		CompletableFuture <?> skillCompletables [] = new CompletableFuture<?> [request.getKeys().size()];
		
		for(int i = 0 ; i < ninjaCompletables.length ; i++) {
			ninjaCompletables[i] = ninjaService.getNinjaByName(request.getKeys().get(i).getNinja());
		}
		
		for(int i = 0 ; i < skillCompletables.length ; i++) {
			ninjaCompletables[i] = skillService.findByNinjaAndTypeAsync(request.getKeys().get(i).getNinja(),
					request.getKeys().get(i).getType());
		}
		
		for(CompletableFuture <?> completable : ninjaCompletables) {
			ninjas.add((Ninja) completable.get());
		}
		
		
		
		return null;
	}
	
	@GetMapping("/createFormation23")
	public Map<String,SkillType>  getNinjaComboFormations3(){
		Map<String,SkillType>  map = new HashMap<>();
		map.put("Hidan", SkillType.SKILL);
		map.put("Hotaru", SkillType.NORMAL_ATTACK);
		
		return map;
		
	}
	@GetMapping("/createFormation2")
	public FormationNinjaDTO getNinjaComboFormations(@RequestBody HashMap<String,SkillType> request,
			@RequestParam(value = "awakenings", required = false, defaultValue = "true") boolean awakenings
			) throws InterruptedException, ExecutionException{
		List <Ninja> ninjas = new ArrayList<>();
		List <NinjaSkill> skills = new ArrayList<>();
		//validator.validateAttackSkillDTO(request);
		CompletableFuture <?> ninjaCompletables [] = new CompletableFuture<?> [request.size()];
		CompletableFuture <?> skillCompletables [] = new CompletableFuture<?> [request.size()];
		
		int i = 0;
		for(Map.Entry<String,SkillType> entry : request.entrySet()) {
			ninjaCompletables[i] = ninjaService.getNinjaByName(entry.getKey());
			skillCompletables[i] = skillService.findByNinjaAndTypeAsync(entry.getKey(),entry.getValue());
			i++;
		}
		
		for(CompletableFuture <?> completable : ninjaCompletables) {
			Ninja e = (Ninja) completable.get();
			e.getAwakenings().size();
			e.getSkills().size();
			ninjas.add(e);
			//ninjas.add((Ninja) completable.get());
		}
		
		for(CompletableFuture <?> completable : skillCompletables) {
			skills.add((NinjaSkill) completable.get());
		}
		for(Ninja ninja : ninjas) {
			ninja.getAwakenings().size();
		}
		/*for(Map.Entry<String,SkillType> entry : request.entrySet()) {
			ninjas.add(ninjaService.getNinja(entry.getKey()));
			skills.add(skillService.findByNinjaAndType(entry.getKey(),entry.getValue()));
		}*/
		
		FormationNinjaDTO formation = ninjaService.createFormationWithNinjas(ninjas,true);
		FinalSkillsAttributesDTO finalSkill = new FinalSkillsAttributesDTO();
		finalSkill.setAttributes(skillMapper.toDTOList(skillService.createFinalSkill(skills)));
		finalSkill.setNinjaFormation(formation.getFormationNinjas());
		formation.getFinalSkillsAttributes().add(finalSkill);
		
		return formation;
	}
	
	/*@GetMapping("/createFormation")
	public String getNinjaComboFormations(@RequestBody String [] ninjaNames) throws InterruptedException, ExecutionException{
		List <Ninja> ninjas = new ArrayList<>();
		
		CompletableFuture <?> completables [] = new CompletableFuture<?> [ninjaNames.length];
		
		for(int i = 0 ; i < completables.length ; i++) {
			completables[i] = ninjaServices.getNinjaByName(ninjaNames[i]);
		}
		
		for(CompletableFuture <?> completable : completables) {
			ninjas.add((Ninja) completable.get());
		}
		
		
		return null;
	}*/
	
	
	@GetMapping("/trabajo")
	public String getAll6() throws InterruptedException, IllegalArgumentException, IllegalAccessException {

		Ninja n = ninjaService.getNinja("Hidan");
		Set <String> subFields = new HashSet<>();
		inspect(n,n.getClass(),subFields);
		
		for(String s : subFields) {
			System.out.println(s);
		}
		return null;
	}
	
	static <T> void inspect(Object instance,Class<T> klazz,Set <String> subFields) throws IllegalArgumentException, IllegalAccessException { 
		Field[] fields = klazz.getDeclaredFields(); 
		//System.out.printf("%d fields:%n", fields.length);
		for (Field field : fields) 
		{ 
			//System.out.println(field.getAnnotatedType().getType().getTypeName());
			String clase = field.getType().getSimpleName();
			if( clase.contains("Entity") && subFields.add(clase)) {
				//inspect(field.getType(),subFields);
				field.setAccessible(true);
				//Object newInstance = field.get(instance);
				inspect(null,field.getType(),subFields);
				//System.out.println(field.get(instance));
				//System.out.println(clase);
			}
			if(clase.contains("List") ) {
				ParameterizedType elementParameterizedType = (ParameterizedType) field.getGenericType();
				Type[] friendsType = elementParameterizedType.getActualTypeArguments();
				Class<?> userClass = (Class<?>) friendsType[0];
				clase = userClass.getSimpleName();
				if( subFields.add(clase)) {
					//inspect(userClass,subFields);
					///System.out.println(clase);
					//System.out.printf("Lista de tipo %s %s %s%n", Modifier.toString(field.getModifiers()), userClass.getSimpleName(), field.getName() );
				}
				
			}
		}
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
