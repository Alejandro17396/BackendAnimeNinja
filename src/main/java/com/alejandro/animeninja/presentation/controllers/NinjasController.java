package com.alejandro.animeninja.presentation.controllers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.alejandro.animeninja.bussines.mappers.SkillAttributeMapper;
import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.NinjaEquipment;
import com.alejandro.animeninja.bussines.model.NinjaSkill;
import com.alejandro.animeninja.bussines.model.Pagination;
import com.alejandro.animeninja.bussines.model.SkillType;
import com.alejandro.animeninja.bussines.model.dto.CreateComboNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.FinalSkillsAttributesDTO;
import com.alejandro.animeninja.bussines.model.dto.FormationNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.FormationsNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjasDTO;
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
		List <FormationNinjaDTO> list = ninjaService.getNinjaComboFormations(externFilter, merge, sorted, filtred, or, awakenings);
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
	public FormationNinjaDTO getNinjaComboFormations(@RequestBody HashMap<String,SkillType> request,
			@RequestParam(value = "awakenings", required = false, defaultValue = "true") boolean awakenings
			) throws InterruptedException, ExecutionException{
		List <Ninja> ninjas = new ArrayList<>();
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
		formation.getFinalSkillsAttributes().add(finalSkill);
		
		return formation;
	}
	
	@Autowired
	private NinjaEquipmentRepository repository1;
	
	@GetMapping("/equipment")
	public List <NinjaEquipment> getNinjaEquipment() {
		
		List <NinjaEquipment> list = repository1.findAll();
		return list;
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
