package com.alejandro.animeninja.bussines.validators.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.exceptions.UserFormationException;
import com.alejandro.animeninja.bussines.model.dto.CreateNinjaEquipmentDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateUserFormationDTO;
import com.alejandro.animeninja.bussines.validators.ValidatorCreateNinjaEquipment;
import com.alejandro.animeninja.bussines.validators.ValidatorUserFormation;

@Service
public class ValidatorUserFormationImpl implements ValidatorUserFormation {
	
	@Autowired
	private ValidatorCreateNinjaEquipment validatorCreateNinjaEquipment;

	@Override
	public void validateCreateUserFormationDTO(CreateUserFormationDTO field) {

		if(field == null) {
			throw new UserFormationException("400","Request body cant be null",HttpStatus.BAD_REQUEST);
		}
		
		if(field.getName() == null) {
			throw new UserFormationException("400","field name cant be null",HttpStatus.BAD_REQUEST);
		}
		
		if(field.getNinjas() == null) {
			throw new UserFormationException("400","field ninjas cant be null",HttpStatus.BAD_REQUEST);
		}
		
		if(field.getNinjas().size() > 4) {
			throw new UserFormationException("400","the number of ninjas cant be higher than 4",HttpStatus.BAD_REQUEST);
		}
		
		Set <String> ninjas = new HashSet<>();
		
		for(CreateNinjaEquipmentDTO  ninja : field.getNinjas()) {
			
			if(ninja != null) {
				validatorCreateNinjaEquipment.validateNinja(ninja);
			}
			
			if(!ninjas.add(ninja.getNinja())) {
				throw new UserFormationException("400","same ninja cant be twice in the same formation",HttpStatus.BAD_REQUEST);
			}
		}
		

	}

}
