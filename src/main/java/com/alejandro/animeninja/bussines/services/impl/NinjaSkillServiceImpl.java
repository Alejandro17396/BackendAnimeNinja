package com.alejandro.animeninja.bussines.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.model.NinjaSkill;
import com.alejandro.animeninja.bussines.model.NinjaSkillKey;
import com.alejandro.animeninja.bussines.model.SkillAtributeKey;
import com.alejandro.animeninja.bussines.model.SkillAttribute;
import com.alejandro.animeninja.bussines.model.SkillType;
import com.alejandro.animeninja.bussines.services.NinjaSkillService;
import com.alejandro.animeninja.integration.repositories.NinjaSkillRepository;

@Service
public class NinjaSkillServiceImpl implements NinjaSkillService {

	@Autowired
	private NinjaSkillRepository ninjaSkillRepository;

	@Override
	public List<NinjaSkill> getAll() {
		return ninjaSkillRepository.findAll();
	}

	@Override
	public NinjaSkill getById(NinjaSkillKey key) {
		Optional<NinjaSkill> aux = ninjaSkillRepository.findById(key);
		return aux.isPresent() ? aux.get() : null;
	}

	@Override
	public List <SkillAttribute> createSkill(List<NinjaSkillKey> skillsKeys) {
		
		if(skillsKeys == null || skillsKeys.isEmpty()) {
			return null;
		}
		
		List <NinjaSkill> skills = new ArrayList<>();
		for(NinjaSkillKey key : skillsKeys) {
			NinjaSkill skill = getById(key);
			if(skill != null) {
				skills.add(skill);
			}
		}
		
		Map<SkillAtributeKey,SkillAttribute> mapa = new HashMap<>();
		
		for(NinjaSkill skill : skills) {
			mapa.putAll(skill.getAttributes().stream().collect(Collectors.toMap(SkillAttribute::getKey, attr -> attr)));
		}
		
		return  mapa.values().stream().collect(Collectors.toList())
				.stream().filter(attribute -> !attribute.getAction().equals("attack")).collect(Collectors.toList());
		
	}

	@Override
	public List<SkillAttribute> createSkill2(List<NinjaSkillKey> skillsKeys) {
		if(skillsKeys == null || skillsKeys.isEmpty()) {
			return null;
		}
		
		List <NinjaSkill> skills = new ArrayList<>();
		for(NinjaSkillKey key : skillsKeys) {
			NinjaSkill skill = findByNinjaAndType(key.getNombre(), key.getType());
			if(skill != null) {
				skills.add(skill);
			}
		}
		
		Map<SkillAtributeKey,SkillAttribute> mapa = new HashMap<>();
		
		for(NinjaSkill skill : skills) {
			mapa.putAll(skill.getAttributes().stream().collect(Collectors.toMap(SkillAttribute::getKey, attr -> attr)));
		}
		
		return  mapa.values().stream().collect(Collectors.toList())
				.stream().filter(attribute -> !attribute.getAction().equals("attack")).collect(Collectors.toList());
		
	}

	@Override
	public NinjaSkill findByNinjaAndType(String name, SkillType type) {
		return ninjaSkillRepository.findByNinjaAndType(name, type);
	}

}
