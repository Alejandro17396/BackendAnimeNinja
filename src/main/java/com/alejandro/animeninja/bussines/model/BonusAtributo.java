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
	protected long idBonus;
	
	@Id
	@Column(name="nombre_equipo")
	@JsonIgnore
	protected String nombreEquipo;
	
	@Id
	@Column(name="nombre_atributo")
	protected String nombreAtributo;
	
	@Column(name="valor")
	protected long valor;
	
	@Column(name="accion")
	protected String action;
	
	@Column(name="afecta")
	protected String impact;
	
	@Column(name="condicion")
	protected String condition;
	
	@Column(name="tiempo")
	protected String time;

	@Override
	public int hashCode() {
		return Objects.hash(condition, nombreAtributo, action,impact);
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
		return  Objects.equals(nombreAtributo, other.getNombreAtributo())
				&& Objects.equals(action, other.getAction())
				&& Objects.equals(condition, other.getCondition())
				&& Objects.equals(impact, other.getImpact());
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

	public BonusAtributo() {
		super();
	}
	
	public BonusAtributo(BonusAtributo bonus) {
		this.action = bonus.action;
		this.condition = bonus.condition;
		this.idBonus = bonus.idBonus;
		this.impact = bonus.impact;
		this.nombreAtributo = bonus.nombreAtributo;
		this.nombreEquipo = bonus.nombreEquipo;
		this.time = bonus.time;
	}

}
