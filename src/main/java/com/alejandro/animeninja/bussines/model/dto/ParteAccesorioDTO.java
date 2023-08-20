package com.alejandro.animeninja.bussines.model.dto;


import java.io.Serializable;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParteAccesorioDTO implements Serializable{

	private String nombre;
	private Long valor;
	private String tipo;
	private Atributo atributo;

	//@JsonDeserialize(using = Base64ToByteArrayDeserializer.class)
	//@JsonIgnore
	@JsonProperty 
	private byte[] image;
	@JsonIgnore
    private String setName;
	
	
	public byte[] getImage() {
		return image;
	}

	@JsonIgnore
	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getSetName() {
		return setName;
	}

	public void setSetName(String setName) {
		this.setName = setName;
	}

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
