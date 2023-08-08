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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Bonus_atributo")
@IdClass(ClaveBonusAtributo.class)
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
	
	/*@Id
	@Column(name="nombre_atributo")
	//@Transient
	protected String nombreAtributo;*/
	
	@Id
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
	@JoinColumn(name="nombre_atributo", referencedColumnName = "nombre",insertable=false,updatable=false)
	protected Atributo atributo;
	
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
	
	@Transient
	private String nombreAtributo;

	public String getNombreAtributo() {
		return nombreAtributo;
	}

	public void setNombreAtributo(String nombreAtributo) {
		this.atributo = new Atributo(nombreAtributo);
		this.nombreAtributo = nombreAtributo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(condition, atributo, action,impact);
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
		return  Objects.equals(atributo, other.getAtributo())
				&& Objects.equals(action, other.getAction())
				&& Objects.equals(condition, other.getCondition())
				&& Objects.equals(impact, other.getImpact());
	}
	
	public Atributo getAtributo() {
		return atributo;
	}

	public void setAtributo(Atributo atributo) {
		/*if(atributo!=null) {
			this.nombreAtributo = atributo.getNombre();
		}*/
		this.atributo = atributo;
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

	/*public String getNombreAtributo() {
		return nombreAtributo;
	}

	public void setNombreAtributo(String nombreAtributo) {
		Atributo atributo = new Atributo();
		atributo.setNombre(nombreAtributo);
		this.atributo = atributo;
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

	public BonusAtributo() {
		super();
	}
	
	public BonusAtributo(BonusAtributo bonus) {
		this.action = bonus.action;
		this.condition = bonus.condition;
		this.idBonus = bonus.idBonus;
		this.impact = bonus.impact;
		this.atributo = bonus.atributo;
		this.nombreEquipo = bonus.nombreEquipo;
		this.time = bonus.time;
	}

}
