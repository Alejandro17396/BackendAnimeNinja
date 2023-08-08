package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.Objects;



public class AttributeStatKey implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String ninja;
	private String level;
	private Atributo atributo;
	
	public AttributeStatKey() {
		
	}

	public String getNinja() {
		return ninja;
	}

	public void setNinja(String ninja) {
		this.ninja = ninja;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Atributo getAtributo() {
		return atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(atributo, level, ninja);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AttributeStatKey other = (AttributeStatKey) obj;
		return Objects.equals(atributo, other.atributo) && Objects.equals(level, other.level)
				&& Objects.equals(ninja, other.ninja);
	}
	
	

	/*public String getAttribute_name() {
		return attribute_name;
	}

	public void setAttribute_name(String attribute_name) {
		this.attribute_name = attribute_name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(attribute_name, level, ninja);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AttributeStatKey other = (AttributeStatKey) obj;
		return Objects.equals(attribute_name, other.attribute_name) && Objects.equals(level, other.level)
				&& Objects.equals(ninja, other.ninja);
	}*/
	
	
	
}
