package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="NINJA_SKILLS")
@IdClass(NinjaSkillKey.class)
public class NinjaSkill implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="nombre" )
	private String nombre;
	
	@Id
	@Column(name="ninja",insertable=false, updatable=false)
	@JsonIgnore
	private String ninja;
	
	@Id
	@Column(name="tipo")
	@Enumerated(EnumType.STRING)
	private SkillType type;
	
	@Column(name="texto")
	private String skillText;
	
	@OneToMany
	@JoinColumns( {
	    @JoinColumn(name="nombre_skill", referencedColumnName="nombre",nullable = false),
	    @JoinColumn(name="tipo", referencedColumnName="tipo",nullable = false),
	    @JoinColumn(name="nombre_ninja", referencedColumnName="ninja",nullable = false)} )
	private List <SkillAttribute> attributes;
	
	public NinjaSkill() {
		
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

	public String getTexto() {
		return skillText;
	}

	public void setTexto(String texto) {
		this.skillText = texto;
	}

	public List<SkillAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<SkillAttribute> attributes) {
		this.attributes = attributes;
	}
	
	
	

}
