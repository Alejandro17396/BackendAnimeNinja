package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="BONUSACCESORIOS_ATRIBUTO")
@IdClass(ClaveBonusAccesorioAtributo.class)
public class BonusAccesorioAtributo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="TIPO_BONUS")
	private String tipoBonus;
	
	@Id
	@Column(name="NOMBRE_SET_ACCESORIOS")
	private String nombreSet;
	
	/*@Id
	@Column(name="nombre_atributo")
	private String nombreAtributo;*/
	
	@Id
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
	@JoinColumn(name="nombre_atributo", referencedColumnName = "nombre",insertable=false,updatable=false)
	protected Atributo atributo;
	
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
	
	@Transient
	private String nombreAtributo;

	public String getNombreAtributo() {
		return nombreAtributo;
	}

	public void setNombreAtributo(String nombreAtributo) {
		this.atributo = new Atributo(nombreAtributo);
		this.nombreAtributo = nombreAtributo;
	}
	
	/*@Override
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
		BonusAccesorioAtributo other = (BonusAccesorioAtributo) obj;
		return  Objects.equals(nombreAtributo, other.getNombreAtributo())
				&& Objects.equals(action, other.getAction())
				&& Objects.equals(condition, other.getCondition())
				&& Objects.equals(impact, other.getImpact()		);
	}*/
	
	@Override
	public int hashCode() {
		return Objects.hash(action, atributo, condition, impact);
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
		return Objects.equals(action, other.action) && Objects.equals(atributo, other.atributo)
				&& Objects.equals(condition, other.condition) && Objects.equals(impact, other.impact);
	}

	public Atributo getAtributo() {
		return atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

	public BonusAccesorioAtributo(BonusAccesorioAtributo bonus) {
		this.tipoBonus = bonus.tipoBonus;
		this.nombreSet = bonus.nombreSet;
		//this.nombreAtributo = bonus.nombreAtributo;
		this.atributo = bonus.atributo;
		this.valor = bonus.valor;
		this.action = bonus.action;
		this.impact = bonus.impact;
		this.condition = bonus.condition;
		this.time = bonus.time;
	}

	public BonusAccesorioAtributo() {

	}
	/*@Override
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
	}*/

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

	/*public String getNombreAtributo() {
		return nombreAtributo;
	}

	public void setNombreAtributo(String nombreAtributo) {
		this.nombreAtributo = nombreAtributo;
	}*/

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

	@Override
	public String toString() {
		return "BonusAccesorioAtributo [tipoBonus=" + tipoBonus + ", nombreSet=" + nombreSet + ", atributo=" + atributo
				+ ", valor=" + valor + ", action=" + action + ", impact=" + impact + ", condition=" + condition
				+ ", time=" + time + ", nombreAtributo=" + nombreAtributo + "]";
	}
	
	
	

}
