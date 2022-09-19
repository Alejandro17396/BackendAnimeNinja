package com.alejandro.animeninja.bussines.model.dto;

public class BonusAtributoDTO {

	private long idBonus;
	private String nombreEquipo;
	private String nombreAtributo;
	private long valor;

	public BonusAtributoDTO(long idBonus, String nombreEquipo, String nombreAtributo, long valor) {
		super();
		this.idBonus = idBonus;
		this.nombreEquipo = nombreEquipo;
		this.nombreAtributo = nombreAtributo;
		this.valor = valor;
	}

	public BonusAtributoDTO() {
		super();
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
