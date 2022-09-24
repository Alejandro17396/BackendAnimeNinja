package com.alejandro.animeninja.bussines.model.dto;

public class BonusAtributoDTO {

	private String nombreAtributo;
	private long valor;

	public BonusAtributoDTO( String nombreAtributo, long valor) {
		super();
		this.nombreAtributo = nombreAtributo;
		this.valor = valor;
	}

	public BonusAtributoDTO() {
		super();
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
