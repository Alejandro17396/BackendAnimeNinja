package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

import com.alejandro.animeninja.bussines.model.SkillType;

public class NinjaSkillDTO {

	private String nombre;
	private SkillType type;
	private String skillText;
	private List<SkillAttributeDTO> attributes;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public SkillType getType() {
		return type;
	}

	public void setType(SkillType type) {
		this.type = type;
	}

	public String getSkillText() {
		return skillText;
	}

	public void setSkillText(String skillText) {
		this.skillText = skillText;
	}

	public List<SkillAttributeDTO> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<SkillAttributeDTO> attributes) {
		this.attributes = attributes;
	}

	public NinjaSkillDTO(String nombre, SkillType type, String skillText,
			List<SkillAttributeDTO> attributes) {
		super();
		this.nombre = nombre;
		this.type = type;
		this.skillText = skillText;
		this.attributes = attributes;
	}

	public NinjaSkillDTO() {
		super();
	}

}
