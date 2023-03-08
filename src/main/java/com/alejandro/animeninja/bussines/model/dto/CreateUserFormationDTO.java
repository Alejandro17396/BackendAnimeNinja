package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

public class CreateUserFormationDTO {

	private String name;
	private List<CreateNinjaEquipmentDTO> ninjas;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CreateNinjaEquipmentDTO> getNinjas() {
		return ninjas;
	}

	public void setNinjas(List<CreateNinjaEquipmentDTO> ninjas) {
		this.ninjas = ninjas;
	}

	public CreateUserFormationDTO(String name, List<CreateNinjaEquipmentDTO> ninjas) {
		super();
		this.name = name;
		this.ninjas = ninjas;
	}

	public CreateUserFormationDTO() {
		super();
	}

}
