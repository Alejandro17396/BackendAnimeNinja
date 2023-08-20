package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SET_ACCESORIOS")
public class SetAccesorio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="NOMBRE")
	private String nombre;
	

	@Column(name="EDAD")
	private String edad;
	
	// Añadir el campo para la fecha de salida
	@Temporal(TemporalType.DATE)
    @Column(name="FECHA_SALIDA")
    private Date fechaSalida;
	
	//@Transient
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name="NOMBRE_SET",nullable=false,insertable=false,updatable=false)
	private List <ParteAccesorio>  partes;
	
	//@Transient
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name="NOMBRE_SET_ACCESORIOS",nullable=false,insertable=false,updatable=false)
	private List <BonusAccesorio> bonuses;
	
	public SetAccesorio() {

	}

	
	public String getEdad() {
		return edad;
	}



	public void setEdad(String edad) {
		this.edad = edad;
	}



	public Date getFechaSalida() {
		return fechaSalida;
	}



	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ParteAccesorio> getPartes() {
		return partes;
	}

	public void setPartes(List<ParteAccesorio> partes) {
		this.partes = partes;
	}

	public List<BonusAccesorio> getBonuses() {
		return bonuses;
	}

	public void setBonuses(List<BonusAccesorio> bonuses) {
		this.bonuses = bonuses;
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
		SetAccesorio other = (SetAccesorio) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "SetAccesorio [nombre=" + nombre + ", partes=" + partes + ", bonuses=" + bonuses + "]";
	}
	
	
	

}
