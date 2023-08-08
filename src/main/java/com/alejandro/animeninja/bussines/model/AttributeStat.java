package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="STAT_ATRIBUTO")
@IdClass(AttributeStatKey.class)
public class AttributeStat implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ninja")
	private String ninja;
	
	@Id 
	@Column(name="nivel")
	private String level;
	
	/*@Id
	@Column(name="nombre_atributo")
	private String attribute_name; */
	@Id
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
	@JoinColumn(name="nombre_atributo", referencedColumnName = "nombre",nullable=false,insertable=false,updatable=false)
	protected Atributo atributo;
	
	@Column(name="valor")
	private Long value;
	
	@Transient
	private String attributeName;

	public String getNombreAtributo() {
		return attributeName;
	}

	public void setNombreAtributo(String attributeName) {
		this.atributo = new Atributo(attributeName);
		this.attributeName = attributeName;
	}
	
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

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public Atributo getAtributo() {
		return atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}
	
	
	

}
