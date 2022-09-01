package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="STAT_ATRIBUTO")
@IdClass(AttributeStatKey.class)
public class AttributeStat implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ninja")
	@JsonIgnore
	private String ninja;
	
	@Id 
	@Column(name="nivel")
	@JsonIgnore
	private String level;
	
	@Id
	@Column(name="nombre_atributo")
	private String attribute_name; 
	
	
	@Column(name="valor")
	private Long value;
	
	public AttributeStat() {
		
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

	public String getAttribute_name() {
		return attribute_name;
	}

	public void setAttribute_name(String attribute_name) {
		this.attribute_name = attribute_name;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
	
	
	

}
