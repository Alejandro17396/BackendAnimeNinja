package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="ninja_user_formation")
public class NinjaUserFormation implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="nombre",insertable=false, updatable=false)
	private String nombre;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="id_user_set_accesorios", referencedColumnName="id",nullable=true)
	private UserAccesories accesories;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="id_user_set", referencedColumnName="id",nullable=true)
	private UserSet equipment;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="nombre_ninja", referencedColumnName="nombre",nullable=true)
	private Ninja ninja;
	
	@Column(name="username")
	private String username;
	
	@Column(name="skill")
	@Enumerated(EnumType.STRING)
	private SkillType skill;
	
	@Column(name="chakraNature")
	@Enumerated(EnumType.STRING)
	private	ChakraNature chakraNature;
	
	@Column(name="formation")
	@Enumerated(EnumType.STRING)
	private Formation formation;

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public ChakraNature getChakraNature() {
		return chakraNature;
	}

	public void setChakraNature(ChakraNature chakraNature) {
		this.chakraNature = chakraNature;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public UserAccesories getAccesories() {
		return accesories;
	}

	public void setAccesories(UserAccesories accesories) {
		this.accesories = accesories;
	}
	
	public UserSet getEquipment() {
		return equipment;
	}

	public void setEquipment(UserSet equipment) {
		this.equipment = equipment;
	}

	
	
	public SkillType getSkill() {
		return skill;
	}

	public void setSkill(SkillType skill) {
		this.skill = skill;
	}

	public Ninja getNinja() {
		return ninja;
	}

	public void setNinja(Ninja ninja) {
		this.ninja = ninja;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public NinjaUserFormation(Long id, String nombre, UserAccesories accesories, UserSet equipment, Ninja ninja,
			String username) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.accesories = accesories;
		this.equipment = equipment;
		this.ninja = ninja;
		this.username = username;
	}

	public NinjaUserFormation() {
		super();
	}
	
	
	
	
	
}
