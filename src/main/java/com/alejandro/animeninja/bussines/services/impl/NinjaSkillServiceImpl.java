package com.alejandro.animeninja.bussines.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.NinjaSkill;
import com.alejandro.animeninja.bussines.model.NinjaSkillKey;
import com.alejandro.animeninja.bussines.model.SkillAttributeKey;
import com.alejandro.animeninja.bussines.model.SkillAttribute;
import com.alejandro.animeninja.bussines.model.SkillType;
import com.alejandro.animeninja.bussines.services.NinjaSkillService;
import com.alejandro.animeninja.bussines.utils.PruebasReflection;
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
		
		Map<SkillAttributeKey,SkillAttribute> mapa = new HashMap<>();
		
		for(NinjaSkill skill : skills) {
			mapa.putAll(skill.getAttributes().stream().collect(Collectors.toMap(SkillAttribute::getKey, attr -> attr)));
		}
		
		return  mapa.values().stream().collect(Collectors.toList())
				.stream().filter(attribute -> !attribute.getAction().equals("attack")).collect(Collectors.toList());
		
	}
	
	@Override
	public List <SkillAttribute> createFinalSkill(List<NinjaSkill> skills) {
		
		if(skills == null || skills.isEmpty()) {
			return null;
		}

		Map<SkillAttributeKey,SkillAttribute> mapa = new HashMap<>();
		
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
		
		Map<SkillAttributeKey,SkillAttribute> mapa = new HashMap<>();
		
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
	@Override
	@Async("asyncExecutor")
	@Transactional
	public CompletableFuture< NinjaSkill> findByNinjaAndTypeAsync(String name, SkillType type) {
		NinjaSkill skill = ninjaSkillRepository.findByNinjaAndType(name, type);
		PruebasReflection.getLazyListFromEntity(skill, new HashSet<>());
		/*if(skill != null) {
			try {
			PruebasReflection.callAllGetterMethodsInEntity(skill, new HashSet<>());
			}catch(Exception e1){
				
			}
		}*/
		
		return CompletableFuture.completedFuture(skill);
	}

}
