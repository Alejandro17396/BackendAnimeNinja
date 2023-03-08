package com.alejandro.animeninja.bussines.validators;

import java.util.List;

import com.alejandro.animeninja.bussines.model.dto.CreateNinjaEquipmentDTO;

public interface ValidatorCreateNinjaEquipment {

	void validateNinja(CreateNinjaEquipmentDTO ninja);
	void validateSet(List<String> equipment,String name);
	void validateAccesories(List<String> accesories,String name);
}
