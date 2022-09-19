package com.alejandro.animeninja.bussines.model.dto;

import com.alejandro.animeninja.bussines.model.Atributo;

public class ParteDTO {

	private String nombre;
	private Atributo atributo;
	private Long valor;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Atributo getAtributo() {
		return atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public ParteDTO(String nombre, Atributo atributo, Long valor) {
		super();
		this.nombre = nombre;
		this.atributo = atributo;
		this.valor = valor;
	}

	public ParteDTO() {
		super();
	}

}
