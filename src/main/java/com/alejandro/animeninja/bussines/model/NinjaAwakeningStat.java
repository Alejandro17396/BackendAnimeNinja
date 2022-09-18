package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="NINJA_AWAKENING_ATRIBUTO")
@IdClass(NinjaAwakeningStatKey.class)
public class NinjaAwakeningStat implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="nombre",insertable=false, updatable=false)
	private String name;
	
	@Id
	@Column(name="ninja")
	private String ninja;
	
	@Id
	@Column(name="nivel",insertable=false, updatable=false)
	private String level;
	
	@Id
	@Column(name="nombre_atributo")
	private String attributeName;
	
	@Id
	@Column(name="tipo",insertable=false, updatable=false)
	@Enumerated(EnumType.STRING)
	private SkillType type;
	
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
	
	
	public NinjaAwakeningStat() {
		
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

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public SkillType getType() {
		return type;
	}

	public void setType(SkillType type) {
		this.type = type;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
	
	
}
