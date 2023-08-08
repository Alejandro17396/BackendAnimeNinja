package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ClaveBonusAccesorioAtributo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String tipoBonus;
	private String nombreSet;
	private Atributo atributo;

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

	public Atributo getAtributo() {
		return atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

	public ClaveBonusAccesorioAtributo() {
		super();
	}

}
