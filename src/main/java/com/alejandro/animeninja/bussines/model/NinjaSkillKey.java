package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.Objects;

public class NinjaSkillKey implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String ninja;
	private SkillType type;
	
	public NinjaSkillKey() {
		
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

	@Override
	public int hashCode() {
		return Objects.hash(ninja, nombre, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NinjaSkillKey other = (NinjaSkillKey) obj;
		return Objects.equals(ninja, other.ninja) && Objects.equals(nombre, other.nombre) && type == other.type;
	}
	
	
	
}
