package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

public class AttackSkillDTO {
	
	private List <NinjaSkillKeyDTO> keys;
	
	public AttackSkillDTO() {
		super();
	}

	public AttackSkillDTO(List<NinjaSkillKeyDTO> keys) {
		super();
		this.keys = keys;
	}

	public List<NinjaSkillKeyDTO> getKeys() {
		return keys;
	}

	public void setKeys(List<NinjaSkillKeyDTO> keys) {
		this.keys= keys;
	}
	
	
	

}
