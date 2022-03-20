package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Id;

public class ClaveBonusAccesorio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String tipo;
	private	String nombreAccesorioSet;
	public ClaveBonusAccesorio() {
		
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNombreAccesorioSet() {
		return nombreAccesorioSet;
	}
	public void setNombreAccesorioSet(String nombreAccesorioSet) {
		this.nombreAccesorioSet = nombreAccesorioSet;
	}
	@Override
	public int hashCode() {
		return Objects.hash(nombreAccesorioSet, tipo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClaveBonusAccesorio other = (ClaveBonusAccesorio) obj;
		return Objects.equals(nombreAccesorioSet, other.nombreAccesorioSet) && Objects.equals(tipo, other.tipo);
	}
	@Override
	public String toString() {
		return "ClaveBonusAccesorio [tipo=" + tipo + ", nombreAccesorioSet=" + nombreAccesorioSet + "]";
	}
	
	
}
