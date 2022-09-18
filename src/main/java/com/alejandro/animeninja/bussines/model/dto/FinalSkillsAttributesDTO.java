package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

public class FinalSkillsAttributesDTO {

	private String ninjaFormation;
	private String[] ninjasAttackOrder;
	private List<SkillAttributeDTO> attributes;

	public FinalSkillsAttributesDTO() {
		super();
	}

	public FinalSkillsAttributesDTO(String ninjaFormation, String[] ninjasAttackOrder,
			List<SkillAttributeDTO> attributes) {
		super();
		this.ninjaFormation = ninjaFormation;
		this.ninjasAttackOrder = ninjasAttackOrder;
		this.attributes = attributes;
	}

	public String getNinjaFormation() {
		return ninjaFormation;
	}

	public void setNinjaFormation(String ninjaFormation) {
		this.ninjaFormation = ninjaFormation;
	}

	public String[] getNinjasAttackOrder() {
		return ninjasAttackOrder;
	}

	public void setNinjasAttackOrder(String[] ninjasAttackOrder) {
		this.ninjasAttackOrder = ninjasAttackOrder;
	}

	public List<SkillAttributeDTO> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<SkillAttributeDTO> attributes) {
		this.attributes = attributes;
	}

}
