package com.alejandro.animeninja.bussines.model.dto;

import java.io.Serializable;
import java.util.List;

public class CreateNinjaAttributesDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private NinjaDTO ninja;
	private List<String> attributes;

	public CreateNinjaAttributesDTO() {
		super();
	}

	public NinjaDTO getNinja() {
		return ninja;
	}

	public void setNinja(NinjaDTO ninja) {
		this.ninja = ninja;
	}

	public List<String> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}

}
