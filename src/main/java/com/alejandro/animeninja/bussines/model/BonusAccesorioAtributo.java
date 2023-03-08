package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="BONUSACCESORIOS_ATRIBUTO")
public class BonusAccesorioAtributo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="TIPO_BONUS")
	
	private String tipoBonus;
	
	@Id
	@Column(name="NOMBRE_SET_ACCESORIOS")
	
	private String nombreSet;
	
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
		return Objects.hash(nombreAtributo, nombreSet, tipoBonus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BonusAccesorioAtributo other = (BonusAccesorioAtributo) obj;
		return Objects.equals(nombreAtributo, other.nombreAtributo) && Objects.equals(nombreSet, other.nombreSet)
				&& Objects.equals(tipoBonus, other.tipoBonus);
	}

	@Override
	public String toString() {
		return "BonusAccesorioAtributo [tipoBonus=" + tipoBonus + ", nombreSet=" + nombreSet + ", nombreAtributo="
				+ nombreAtributo + ", valor=" + valor + "]";
	}

	public String getTipoBonus() {
		return tipoBonus;
	}

	public void setTipoBonus(String tipoBonus) {
		this.tipoBonus = tipoBonus;
	}

	public String getNombreSet() {
		return nombreSet;
	}

	public void setNombreSet(String nombreSet) {
		this.nombreSet = nombreSet;
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
