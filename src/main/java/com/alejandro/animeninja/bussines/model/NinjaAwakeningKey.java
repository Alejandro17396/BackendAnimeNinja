package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.Objects;

public class NinjaAwakeningKey implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private SkillType type;
	private String level;
	
	public NinjaAwakeningKey() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SkillType getType() {
		return type;
	}

	public void setType(SkillType type) {
		this.type = type;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public int hashCode() {
		return Objects.hash(level, name, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NinjaAwakeningKey other = (NinjaAwakeningKey) obj;
		return Objects.equals(level, other.level) && Objects.equals(name, other.name) && type == other.type;
	}
	
	
	
	
}
