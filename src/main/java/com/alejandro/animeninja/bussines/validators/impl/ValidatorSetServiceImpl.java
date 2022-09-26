package com.alejandro.animeninja.bussines.validators.impl;

import org.springframework.http.HttpStatus;

import com.alejandro.animeninja.bussines.exceptions.SetException;
import com.alejandro.animeninja.bussines.model.CreateComboSet;
import com.alejandro.animeninja.bussines.validators.ValidatorSetService;

public class ValidatorSetServiceImpl implements ValidatorSetService{

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
