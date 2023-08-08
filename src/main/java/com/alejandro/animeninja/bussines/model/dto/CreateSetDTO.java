package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

public class CreateSetDTO {

	private Long id;
	private List<String> equipment;
	private String setName;
	private String username;	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getEquipment() {
		return equipment;
	}

	public void setEquipment(List<String> equipment) {
		this.equipment = equipment;
	}

	public String getSetName() {
		return setName;
	}

	public void setSetName(String setName) {
		this.setName = setName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CreateSetDTO() {
		super();
	}

}
