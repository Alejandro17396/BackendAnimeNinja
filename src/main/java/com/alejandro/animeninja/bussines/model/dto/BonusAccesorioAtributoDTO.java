package com.alejandro.animeninja.bussines.model.dto;

public class BonusAccesorioAtributoDTO {

	private String nombreAtributo;
	private long valor;

	

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

	public BonusAccesorioAtributoDTO(String nombreAtributo, long valor) {
		super();
		this.nombreAtributo = nombreAtributo;
		this.valor = valor;
	}

	public BonusAccesorioAtributoDTO() {
		super();
	}

}
