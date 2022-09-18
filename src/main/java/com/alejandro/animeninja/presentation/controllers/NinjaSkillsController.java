package com.alejandro.animeninja.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.alejandro.animeninja.bussines.services.NinjaSkillService;

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
	
	@GetMapping("/AttackResult")
	public List <SkillAttributeDTO> getNinjaFormationSkillFinalAttributes(@RequestBody(required = false) AttackSkillDTO request) {

		return skillMapper.toDTOList(ninjaSkillServices.createSkill(keysMapper.toEntityList(request.getKeys())));
	}
	
	@GetMapping("/{ninja}/{type}")
	public NinjaSkillDTO getSkill(@PathVariable String ninja,@PathVariable SkillType type) {
		return ninjaSkillMapper.toDTO(ninjaSkillServices.findByNinjaAndType("Hidan", SkillType.SKILL));
	}

}
