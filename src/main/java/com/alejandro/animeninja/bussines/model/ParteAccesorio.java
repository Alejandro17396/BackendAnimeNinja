package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="PARTES_ACCESORIOS")
public class ParteAccesorio implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="nombre_set")
	private String nombreSet;
	
	@OneToOne
	@JoinColumn(name = "nombre_atributo")
	private Atributo atributo;
	
	@Column(name = "valor")
	private Long valor;

	
	@Column(name="tipo")
	private String tipo;
	public ParteAccesorio() {
	
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
		ParteAccesorio other = (ParteAccesorio) obj;
		return Objects.equals(nombre, other.nombre);
	}

	public String getNombreSet() {
		return nombreSet;
	}

	public void setNombreSet(String nombreSet) {
		this.nombreSet = nombreSet;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Atributo getAtributo() {
		return atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "ParteAccesorio [nombre=" + nombre + ", atributo=" + atributo + ", valor=" + valor + "]";
	}
	
	

}
