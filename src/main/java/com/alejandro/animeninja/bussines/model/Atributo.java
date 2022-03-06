package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ATRIBUTOS")
public class Atributo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="nombre")
	private String nombre;

	public Atributo() {
		super();
	}

	public Atributo(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Atributo [nombre=" + nombre + "]";
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
		Atributo other = (Atributo) obj;
		return Objects.equals(nombre, other.nombre);
	}

}
