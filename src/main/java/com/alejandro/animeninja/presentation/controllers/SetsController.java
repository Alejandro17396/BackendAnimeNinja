package com.alejandro.animeninja.presentation.controllers;



import java.util.List;

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

import com.alejandro.animeninja.bussines.mappers.SetMapper;
import com.alejandro.animeninja.bussines.model.CreateComboSet;
import com.alejandro.animeninja.bussines.model.NinjaEquipment;
import com.alejandro.animeninja.bussines.model.Pagination;
import com.alejandro.animeninja.bussines.model.UserFormation;
import com.alejandro.animeninja.bussines.model.dto.SetDTO;
import com.alejandro.animeninja.bussines.model.dto.SetsDTO;
import com.alejandro.animeninja.bussines.services.EquipoServices;
import com.alejandro.animeninja.bussines.validators.ValidatorNinjaService;
import com.alejandro.animeninja.integration.repositories.NinjaEquipmentRepository;
import com.alejandro.animeninja.integration.repositories.UserFormationRepository;

@RestController
@CrossOrigin
@RequestMapping("/sets")
public class SetsController {

	@Autowired
	private EquipoServices equipoServices;
	
	@Autowired
	private SetMapper setMapper;
	
	@Autowired
	private ValidatorNinjaService validator;
	
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

	
	@GetMapping("/CombinacionesBonusTotal")
	public ResponseEntity <SetsDTO> CombineSetsByAttributesTotal(@RequestBody(required = false) CreateComboSet attributes,
			@RequestParam(value = "sorted", required = false, defaultValue = "true") boolean sorted,
			@RequestParam(value = "filtred", required = false, defaultValue = "true") boolean filtred,
			Pageable pageable) {

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
		return response;
		
	}
	
	@Autowired
	private NinjaEquipmentRepository repository1;
	
	@GetMapping("/equipment")
	public NinjaEquipment getNinjaEquipment() {
		
		List <NinjaEquipment> list = repository1.findAll();
		return list.get(0);
	}
	
	
	@Autowired
	private UserFormationRepository repository;
	
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
	}

}
