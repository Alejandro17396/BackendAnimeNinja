package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

public class CreateUserFormationDTO {

	private Long id;
	private String name;
	private List<CreateNinjaEquipmentDTO> ninjas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
