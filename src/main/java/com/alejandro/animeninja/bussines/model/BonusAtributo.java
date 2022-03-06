package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Bonus_atributo")
public class BonusAtributo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_bonus")
	@JsonIgnore
	private long idBonus;
	
	@Id
	@Column(name="nombre_equipo")
	@JsonIgnore
	private String nombreEquipo;
	
	@Id
	@Column(name="nombre_atributo")
	private String nombreAtributo;
	
	@Column(name="valor")
	private long valor;

	@Override
	public int hashCode() {
		return Objects.hash(idBonus, nombreAtributo, nombreEquipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BonusAtributo other = (BonusAtributo) obj;
		return idBonus == other.idBonus && Objects.equals(nombreAtributo, other.nombreAtributo)
				&& Objects.equals(nombreEquipo, other.nombreEquipo);
	}

	@Override
	public String toString() {
		return "Bonus_atributo [idBonus=" + idBonus + ", nombreEquipo=" + nombreEquipo + ", nombreAtributo="
				+ nombreAtributo + ", valor=" + valor + "]";
	}

	public long getIdBonus() {
		return idBonus;
	}

	public void setIdBonus(long idBonus) {
		this.idBonus = idBonus;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public String getNombreAtributo() {
		return nombreAtributo;
	}

	public void setNombreAtributo(String nombreAtributo) {
		this.nombreAtributo = nombreAtributo;
	}

	public long getValor() {
		return valor;
	}

	public void setValor(long valor) {
		this.valor = valor;
	}
	
	

}
