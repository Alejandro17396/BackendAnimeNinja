package com.alejandro.animeninja.bussines.validators.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.exceptions.NinjaFilterException;
import com.alejandro.animeninja.bussines.exceptions.NinjaSkillException;
import com.alejandro.animeninja.bussines.exceptions.SetException;
import com.alejandro.animeninja.bussines.model.CreateComboSet;
import com.alejandro.animeninja.bussines.model.dto.AttackSkillDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateComboNinjaDTO;
import com.alejandro.animeninja.bussines.validators.ValidatorNinjaService;

@Service
public class ValidatorNinjaServiceImpl implements ValidatorNinjaService {

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

}
