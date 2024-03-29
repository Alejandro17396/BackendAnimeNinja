package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PARTES")
@Embeddable
public class Parte implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "NOMBRE")
	private String nombre;

	@OneToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "nombre_atributo")
	private Atributo atributo;

	@Column(name = "valor")
	private Long valor;
	
	@Column(name="nombre_equipo")
	private String equipo;
	
	@Lob
    @Column(name="image", columnDefinition="BLOB")
    private byte[] image;

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
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

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	@Override
	public String toString() {
		return "Parte [nombre=" + nombre + ", atributo=" + atributo + ", valor=" + valor + "]";
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
		Parte other = (Parte) obj;
		return Objects.equals(nombre, other.nombre);
	}

}
