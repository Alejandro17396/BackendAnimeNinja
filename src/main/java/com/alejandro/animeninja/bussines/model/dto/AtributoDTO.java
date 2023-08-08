package com.alejandro.animeninja.bussines.model.dto;

import java.io.Serializable;
import java.util.Objects;

public class AtributoDTO implements Serializable{

	public AtributoDTO(String nombre) {
		super();
		this.nombre = nombre;
	}

	private static final long serialVersionUID = 1L;
	
	private String nombre;

	public AtributoDTO() {
		super();
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
		AtributoDTO other = (AtributoDTO) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
}
