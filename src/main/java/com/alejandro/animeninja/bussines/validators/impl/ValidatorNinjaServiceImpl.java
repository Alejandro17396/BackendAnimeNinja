package com.alejandro.animeninja.bussines.validators.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.exceptions.AccesoriesException;
import com.alejandro.animeninja.bussines.exceptions.FormationException;
import com.alejandro.animeninja.bussines.exceptions.NinjaFilterException;
import com.alejandro.animeninja.bussines.exceptions.NinjaSkillException;
import com.alejandro.animeninja.bussines.exceptions.SetException;
import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.CreateComboSet;
import com.alejandro.animeninja.bussines.model.CreateComboSetAccesorio;
import com.alejandro.animeninja.bussines.model.Formation;
import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.SkillType;
import com.alejandro.animeninja.bussines.model.dto.AttackSkillDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateComboNinjaDTO;
import com.alejandro.animeninja.bussines.services.NinjaService;
import com.alejandro.animeninja.bussines.validators.ValidatorNinjaService;

@Service
public class ValidatorNinjaServiceImpl implements ValidatorNinjaService {

	@Autowired
	private NinjaService ninjaService;
	
	@Override
	public void validateCreateComboNinjaDTO(CreateComboNinjaDTO field) {
		
		if(field == null) {
			throw new NinjaFilterException("400","Request body cant be null",HttpStatus.BAD_REQUEST);
		}
		
		if(field.getAttributeFilters() == null || field.getAttributeFilters().contains(null)) {
			throw new NinjaFilterException("400","field attributesFilters cant be null or contain null elements",HttpStatus.BAD_REQUEST);
		}
		
		
		if(field.getFilters() == null || field.getFilters().contains(null)) {
			throw new NinjaFilterException("400","field filters cant be null or contain null elements",HttpStatus.BAD_REQUEST);
		}
		
		if(field.getOrder() == null || field.getOrder().contains(null)) {
			throw new NinjaFilterException("400","field order cant be null or contain null elements",HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public void validateAttackSkillDTO(AttackSkillDTO field) {

		if(field == null) {
			throw new NinjaSkillException("400", "Request body cant be null",HttpStatus.BAD_REQUEST);
		}
		
		if(field.getKeys() == null || field.getKeys().contains(null)){
			throw new NinjaSkillException("400", "field keys cant be null or contai null elements",HttpStatus.BAD_REQUEST);
		}
		
	}

	@Override
	public void validateCreateComboSet(CreateComboSet field) {
		
		if(field == null) {
			throw new SetException("400","Request body cant be null",HttpStatus.BAD_REQUEST);
		}
		
		if(field.getAttributes() == null || field.getAttributes().contains(null)) {
			throw new SetException("400","field attributes cant be null or contain null elements",HttpStatus.BAD_REQUEST);
		}
		
		if(field.getAttributesFilter() == null || field.getAttributesFilter().contains(null)) {
			throw new SetException("400","field attributesFilter cant be null or contain null elements",HttpStatus.BAD_REQUEST);
		}
		
		if(field.getOrder() == null || field.getOrder().contains(null)) {
			throw new SetException("400","field order cant be null or contain null elements",HttpStatus.BAD_REQUEST);
		}
		
		if(field.getSetName() == null) {
			throw new SetException("400","field setName cant be null",HttpStatus.BAD_REQUEST);
		}
		
	}

	@Override
	public void validateCreateComboSetAccesorio(CreateComboSetAccesorio field) {

		if(field == null) {
			throw new AccesoriesException("400","Request body cant be null",HttpStatus.BAD_REQUEST);
		}
		
		if(field.getAttributes() == null || field.getAttributes().contains(null)) {
			throw new AccesoriesException("400","field attributes cant be null or contain null elements",HttpStatus.BAD_REQUEST);
		}
		
		if(field.getAttributesFilter() == null || field.getAttributesFilter().contains(null)) {
			throw new AccesoriesException("400","field attributesFilter cant be null or contain null elements",HttpStatus.BAD_REQUEST);
		}
		
		if(field.getOrder() == null || field.getOrder().contains(null)) {
			throw new AccesoriesException("400","field order cant be null or contain null elements",HttpStatus.BAD_REQUEST);
		}
		
		if(field.getSetFilter() == null) {
			throw new AccesoriesException("400","field setName cant be null",HttpStatus.BAD_REQUEST);
		}
		
	}

	@Override
	public void validateAttributesList(List<Atributo> attributes) {
		
		if (attributes == null || attributes.contains(null)) {
			throw new AccesoriesException("400","field attributes cant be null or contain null elements",HttpStatus.BAD_REQUEST);
		}
		
	}

	@Override
	public void validateCanCreateFormation(HashMap<String, SkillType> request) {
		if(request == null  || request.containsKey(null) || request.containsValue(null)){
			throw new NinjaFilterException("400","Ninja Map cant be null or cantains null elements",HttpStatus.BAD_REQUEST);
		}
		
		List <Ninja> ninjas = new ArrayList<>();
		for(Map.Entry<String,SkillType> entry : request.entrySet()) {
			Ninja ninja = ninjaService.getNinja(entry.getKey());
			if(ninja != null) {
				ninjas.add(ninja);
			}
		}
		
		Map<Formation, Long> mapa = new HashMap<>();
		for(Ninja n : ninjas) {
			if(mapa.containsKey(n.getFormation())) {
				mapa.put(n.getFormation(), mapa.get(n.getFormation()) + 1L);
			}else {
				mapa.put(n.getFormation(), 1L);
			}
		}
		
		for(Map.Entry<Formation, Long> entry : mapa.entrySet()) {
			if(entry.getKey().equals(Formation.ASSAULTER) && entry.getValue() > 3) {
				throw new FormationException("400","Formation cant contain more than 3 assaulters",HttpStatus.BAD_REQUEST);
			}
			if(entry.getKey().equals(Formation.SUPPORT) && entry.getValue() > 3) {
				throw new FormationException("400","Formation cant contain more than 3 supports",HttpStatus.BAD_REQUEST);
			}
			if(entry.getKey().equals(Formation.VANGUARD) && entry.getValue() > 1) {
				throw new FormationException("400","Formation cant contain more than 1 vanguard",HttpStatus.BAD_REQUEST);
			}
		}
		
	}

}
