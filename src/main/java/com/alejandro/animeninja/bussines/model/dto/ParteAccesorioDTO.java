package com.alejandro.animeninja.bussines.model.dto;

public class ParteAccesorioDTO {

	private String nombre;
	private Long valor;
	private String tipo;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public ParteAccesorioDTO(String nombre, Long valor, String tipo) {
		super();
		this.nombre = nombre;
		this.valor = valor;
		this.tipo = tipo;
	}

	public ParteAccesorioDTO() {
		super();
	}

}
