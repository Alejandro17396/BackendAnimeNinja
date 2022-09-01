package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Id;

public class SkillAtributeKey implements Serializable{

	private static final long serialVersionUID = 1L;
	private String skillName;
	private String ninjaName;
	private SkillType type;
	private String attributeName;
	private String action;
	private String impact;
	
	public SkillAtributeKey() {
		
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getNinjaName() {
		return ninjaName;
	}

	public void setNinjaName(String ninjaName) {
		this.ninjaName = ninjaName;
	}

	public SkillType getType() {
		return type;
	}

	public void setType(SkillType type) {
		this.type = type;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
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
		return Objects.hash(action, attributeName, impact, ninjaName, skillName, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SkillAtributeKey other = (SkillAtributeKey) obj;
		return Objects.equals(action, other.action) && Objects.equals(attributeName, other.attributeName)
				&& Objects.equals(impact, other.impact) && Objects.equals(ninjaName, other.ninjaName)
				&& Objects.equals(skillName, other.skillName) && type == other.type;
	}

	
	
}
