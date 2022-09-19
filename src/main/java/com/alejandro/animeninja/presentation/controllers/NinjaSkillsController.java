package com.alejandro.animeninja.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.mappers.NinjaSkillKeyMapper;
import com.alejandro.animeninja.bussines.mappers.NinjaSkillMapper;
import com.alejandro.animeninja.bussines.mappers.SkillAttributeMapper;
import com.alejandro.animeninja.bussines.model.SkillType;
import com.alejandro.animeninja.bussines.model.dto.AttackSkillDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaSkillDTO;
import com.alejandro.animeninja.bussines.model.dto.SkillAttributeDTO;
import com.alejandro.animeninja.bussines.model.dto.SkillResultDTO;
import com.alejandro.animeninja.bussines.services.NinjaSkillService;
import com.alejandro.animeninja.bussines.services.ValidatorService;

@RestController
@CrossOrigin
@RequestMapping("/skills")
public class NinjaSkillsController {
	
	@Autowired
	private NinjaSkillService ninjaSkillServices;
	
	@Autowired
	private SkillAttributeMapper skillMapper;
	
	@Autowired
	private NinjaSkillKeyMapper keysMapper;
	
	@Autowired
	private NinjaSkillMapper ninjaSkillMapper;
	

	@Autowired
	private ValidatorService validator;
	
	@GetMapping("/AttackResult")
	public ResponseEntity <SkillResultDTO> getNinjaFormationSkillFinalAttributes(@RequestBody(required = false) AttackSkillDTO request) {

		validator.validateAttackSkillDTO(request);
		List <SkillAttributeDTO> list =skillMapper.toDTOList(ninjaSkillServices.createSkill(keysMapper.toEntityList(request.getKeys())));
		SkillResultDTO responseDTO = new SkillResultDTO();
		responseDTO.setSkillFinalAttributes(list);
		responseDTO.setAttributesNumber(responseDTO.getSkillFinalAttributes().size());
		ResponseEntity <SkillResultDTO> response = null;
		if(responseDTO.getAttributesNumber() > 0) {
			response = new ResponseEntity <>(responseDTO,HttpStatus.OK);
		}else {
			response = new ResponseEntity<>(responseDTO,HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/{ninja}/{type}")
	public ResponseEntity <NinjaSkillDTO> getSkill(@PathVariable String ninja,@PathVariable SkillType type) {
		
		NinjaSkillDTO responseDTO = ninjaSkillMapper.toDTO(ninjaSkillServices.findByNinjaAndType("Hidan", SkillType.SKILL));
		ResponseEntity <NinjaSkillDTO> response = null;
		if(responseDTO != null) {
			response = new ResponseEntity <>(responseDTO,HttpStatus.OK);
		}else {
			response = new ResponseEntity<>(responseDTO,HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	

}
