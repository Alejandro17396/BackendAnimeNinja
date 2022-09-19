package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

public class SkillResultDTO {

	private List<SkillAttributeDTO> skillFinalAttributes;
	private int attributesNumber;

	public SkillResultDTO() {
		super();
	}

	public List<SkillAttributeDTO> getSkillFinalAttributes() {
		return skillFinalAttributes;
	}

	public void setSkillFinalAttributes(List<SkillAttributeDTO> skillFinalAttributes) {
		this.skillFinalAttributes = skillFinalAttributes;
	}

	public int getAttributesNumber() {
		return attributesNumber;
	}

	public void setAttributesNumber(int attributesNumber) {
		this.attributesNumber = attributesNumber;
	}

}
