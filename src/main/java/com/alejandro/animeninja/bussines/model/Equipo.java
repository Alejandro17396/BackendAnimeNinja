package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="EQUIPOS")
public class Equipo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	@Id
	@Column(name="NOMBRE")
	private String nombre;

	@OneToMany
	@JoinColumn(name="nombre_equipo")
	private List <Parte> partes;
	
	//@Transient
	@OneToMany
	@JoinColumn(name="nombre_equipo")
	private List <Bonus> bonuses;
	
	
	public List<Parte> getPartes() {
		return partes;
	}



	public void setPartes(List<Parte> partes) {
		this.partes = partes;
	}



	public List<Bonus> getBonuses() {
		return bonuses;
	}



	public void setBonuses(List<Bonus> bonuses) {
		this.bonuses = bonuses;
	}



	public Equipo() {
		
	}
	
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo other = (Equipo) obj;
		return Objects.equals(nombre, other.nombre);
	}



	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", partes=" + partes + ", bonuses=" + bonuses + "]";
	}



	

	

	
}
