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
	
	@Column(name="accion")
	private String action;
	
	@Column(name="afecta")
	private String impact;
	
	@Column(name="condicion")
	private String condition;
	
	@Column(name="tiempo")
	private String time;

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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getImpact() {
		return impact;
	}

	public void setImpact(String impact) {
		this.impact = impact;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	

}
