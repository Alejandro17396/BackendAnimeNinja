package com.alejandro.animeninja.bussines.model.dto;

import com.alejandro.animeninja.bussines.model.SkillType;

public class NinjaSkillKeyDTO {

	private String nombre;
	private String ninja;
	private SkillType type;

	public NinjaSkillKeyDTO() {
		super();
	}

	public NinjaSkillKeyDTO(String nombre, String ninja, SkillType type) {
		super();
		this.nombre = nombre;
		this.ninja = ninja;
		this.type = type;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNinja() {
		return ninja;
	}

	public void setNinja(String ninja) {
		this.ninja = ninja;
	}

	public SkillType getType() {
		return type;
	}

	public void setType(SkillType type) {
		this.type = type;
	}

}
