package com.alejandro.animeninja.bussines.model.dto;

import com.alejandro.animeninja.bussines.jsonDeserializers.Base64ToByteArrayDeserializer;
import com.alejandro.animeninja.bussines.model.Atributo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParteDTO {

	private String nombre;
	private Atributo atributo;
	private Long valor;
	
	@JsonDeserialize(using = Base64ToByteArrayDeserializer.class)
	//@JsonProperty
	private byte[] image;
	@JsonIgnore
    private String setName;

	public byte[] getImage() {
		return image;
	}

	//@JsonIgnore
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
