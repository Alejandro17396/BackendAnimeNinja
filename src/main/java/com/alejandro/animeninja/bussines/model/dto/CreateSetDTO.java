package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

public class CreateSetDTO {

	private List<String> equipment;
	private String setName;

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

	public CreateSetDTO() {
		super();
	}

}
