package com.alejandro.animeninja.bussines.model.dto;


public class AttributeStatDTO {

	
	private String attribute_name;
	private Long value;
	private AtributoDTO atributo;

	public AtributoDTO getAtributo() {
		return atributo;
	}

	public void setAtributo(AtributoDTO atributo) {
		if(atributo != null) {
			this.attribute_name = atributo.getNombre();
		}
		this.atributo = atributo;
	}

	public String getAttribute_name() {
		return attribute_name;
	}

	public void setAttribute_name(String attribute_name) {
		this.atributo = new AtributoDTO(attribute_name);
		this.attribute_name = attribute_name;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public AttributeStatDTO(String attribute_name, Long value) {
		super();
		this.attribute_name = attribute_name;
		this.value = value;
	}

	public AttributeStatDTO() {
		super();
	}

}
