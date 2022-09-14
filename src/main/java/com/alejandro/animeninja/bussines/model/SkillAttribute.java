package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="SKILL_ATRIBUTO")
@IdClass(SkillAtributeKey.class)
public class SkillAttribute implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="nombre_skill",insertable=false, updatable=false)
	private String skillName;
	
	@Id
	@Column(name="nombre_ninja",insertable=false, updatable=false)
	private String ninjaName;
	
	@Id
	@Column(name="tipo",insertable=false, updatable=false)
	@Enumerated(EnumType.STRING)
	private SkillType type;
	
	@Id
	@Column(name="nombre_atributo")
	private String attributeName;
	
	@Id
	@Column(name="accion")
	private String action;
	
	@Id
	@Column(name="afecta")
	private String impact;
	
	@Column(name="valor")
	private Long value;
	
	@Column(name="condicion")
	private String condition;
	
	@Column(name="tiempo")
	private String time;
	
	public SkillAttribute() {
		
	}

	

	public String getCondition() {
		return condition;
	}



	public void setCondition(String condition) {
		this.condition = condition;
	}



	public String getTime() {
		return time;
	}



	public void setTime(String time) {
		this.time = time;
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


	public Long getValue() {
		return value;
	}


	public void setValue(Long value) {
		this.value = value;
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
		SkillAttribute other = (SkillAttribute) obj;
		return Objects.equals(action, other.action) && Objects.equals(attributeName, other.attributeName)
				&& Objects.equals(impact, other.impact) && Objects.equals(ninjaName, other.ninjaName)
				&& Objects.equals(skillName, other.skillName) && type == other.type;
	}



	public boolean canBeMerged(SkillAttribute other) {
		if (this == other)
			return false;
		if (other == null)
			return false;
		if (getClass() != other.getClass())
			return false;
		if(this.attributeName.equals(other.attributeName) && this.type == other.type 
				&& this.impact.equals(other.impact) && this.action.equals(other.action) 
				&& this.condition.equals(other.condition)) {
			return true;
		}
		
		
		return false;
	}
	
	public SkillAttribute clone() {
		
		SkillAttribute other = new SkillAttribute();
		
		other.action=this.action;
		other.attributeName=this.attributeName;
		other.condition=this.condition;
		other.impact=this.impact;
		other.type=this.type;
		other.time=this.time;
		other.value=this.value;
		
		
		return other;
	}

	
	public boolean canBeIncreasedBy(SkillAttribute attribute) {
		if(attribute == null) {
			return false;
		}
		if(this == attribute) {
			return false;
		}
		
		return this.getAttributeName().equals(attribute.getAttributeName()) 
				&& this.getAction().equals(attribute.getAction())
				&& this.getCondition().equals(attribute.getCondition());
		
	}
	public boolean canBeCompared(SkillAttribute attribute) {
		if(attribute == null) {
			return false;
		}
		if(this == attribute) {
			return false;
		}
		
		return this.getAttributeName().equals(attribute.getAttributeName()) 
				&& this.getAction().equals(attribute.getAction())
				&& this.getImpact().equals(attribute.getImpact());
		
	}
}
