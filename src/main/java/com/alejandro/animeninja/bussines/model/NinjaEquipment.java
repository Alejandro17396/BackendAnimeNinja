package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="ninja_equipment")
public class NinjaEquipment implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@Column(name="nombre",insertable=false, updatable=false)
	private String nombre;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="accesories", referencedColumnName="nombre",nullable = false)
	private SetAccesorio accesories;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="equipment", referencedColumnName="nombre",nullable = false)
	private Equipo equipment;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="ninja", referencedColumnName="nombre",nullable = false)
	private Ninja ninja;
	
	public NinjaEquipment() {
		
	}

	public NinjaEquipment(Long id, String nombre, SetAccesorio accesories, Equipo equipment, Ninja ninja) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.accesories = accesories;
		this.equipment = equipment;
		this.ninja = ninja;
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

	public SetAccesorio getAccesories() {
		return accesories;
	}

	public void setAccesories(SetAccesorio accesories) {
		this.accesories = accesories;
	}


	public Equipo getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipo equipment) {
		this.equipment = equipment;
	}

	public Ninja getNinja() {
		return ninja;
	}

	public void setNinja(Ninja ninja) {
		this.ninja = ninja;
	}
	
}
