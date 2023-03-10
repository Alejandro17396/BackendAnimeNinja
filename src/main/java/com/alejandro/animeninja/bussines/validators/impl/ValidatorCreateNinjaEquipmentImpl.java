package com.alejandro.animeninja.bussines.validators.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.exceptions.CreateAccesoriesException;
import com.alejandro.animeninja.bussines.exceptions.CreateNinjaException;
import com.alejandro.animeninja.bussines.exceptions.CreateSetException;
import com.alejandro.animeninja.bussines.model.dto.CreateNinjaEquipmentDTO;
import com.alejandro.animeninja.bussines.validators.ValidatorCreateNinjaEquipment;

@Service
public class ValidatorCreateNinjaEquipmentImpl implements ValidatorCreateNinjaEquipment {

	@Override
	public void validateNinja(CreateNinjaEquipmentDTO ninja) {
		if(ninja == null) {
			throw new CreateNinjaException("400","ninja cant be null",HttpStatus.BAD_REQUEST);
		}
		
		if(ninja.getName() == null) {
			throw new CreateNinjaException("400","ninja name cant be null",HttpStatus.BAD_REQUEST);
		}
		
		if(ninja.getNinja() == null) {
			throw new CreateNinjaException("400","ninja name cant be null",HttpStatus.BAD_REQUEST);
		}
		
		if(ninja.getSet() != null) {
			validateSet(ninja.getSet().getEquipment(),ninja.getName());
		}
		
		if(ninja.getAccesories() != null) {
			validateAccesories(ninja.getAccesories().getAccesories(),ninja.getAccesories().getAccesorieSetName());
		}
		
	}

	@Override
	public void validateSet(List<String> equipment, String name) {
		if(equipment == null || equipment.isEmpty()) {
			throw new CreateSetException("400","equipment list cant be null or empty",HttpStatus.BAD_REQUEST);
		}
		
		if(name == null) {
			throw new CreateSetException("400","name cant be null",HttpStatus.BAD_REQUEST);
		}
		
	}

	@Override
	public void validateAccesories(List<String> accesories, String name) {
		if(accesories == null || accesories.isEmpty()) {
			throw new CreateAccesoriesException("400","accesorie list cant be null or empty",HttpStatus.BAD_REQUEST);
		}
		
		if(name == null) {
			throw new CreateAccesoriesException("400","name cant be null",HttpStatus.BAD_REQUEST);
		}
		
	}

}
