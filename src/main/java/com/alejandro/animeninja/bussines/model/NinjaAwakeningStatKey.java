package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.Objects;

public class NinjaAwakeningStatKey implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String ninja;
	private String level;
	private Atributo atributo;
	private SkillType type;
	private String action;
	private String impact;
	public NinjaAwakeningStatKey() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public SkillType getType() {
		return type;
	}

	public void setType(SkillType type) {
		this.type = type;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getImpact() {
		return impact;
	}

	public void setImpact(String impact) {
		this.impact = impact;
	}

	@Override
	public int hashCode() {
		return Objects.hash(action, atributo, impact, level, name, ninja, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NinjaAwakeningStatKey other = (NinjaAwakeningStatKey) obj;
		return Objects.equals(action, other.action) && Objects.equals(atributo, other.atributo)
				&& Objects.equals(impact, other.impact) && Objects.equals(level, other.level)
				&& Objects.equals(name, other.name) && Objects.equals(ninja, other.ninja) && type == other.type;
	}

	
	
	
	
}
