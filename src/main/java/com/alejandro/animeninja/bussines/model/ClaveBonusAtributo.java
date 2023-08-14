package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;

public class ClaveBonusAtributo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idBonus;
	private String nombreEquipo;
	//private String nombreAtributo;
	private Atributo atributo;
	private String impact;

	public Atributo getAtributo() {
		return atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

	// private String nombreAtributo;
	public Long getIdBonus() {
		return idBonus;
	}

	public void setIdBonus(Long idBonus) {
		this.idBonus = idBonus;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public String getImpact() {
		return impact;
	}

	public void setImpact(String impact) {
		this.impact = impact;
	}
	
	
	  /*public String getNombreAtributo() { return nombreAtributo; } 
	  public void setNombreAtributo(String nombreAtributo) {
	  this.nombreAtributo = nombreAtributo; }*/
	

}
