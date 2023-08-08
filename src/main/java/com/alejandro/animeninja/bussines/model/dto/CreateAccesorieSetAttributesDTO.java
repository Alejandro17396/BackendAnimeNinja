package com.alejandro.animeninja.bussines.model.dto;

import java.io.Serializable;
import java.util.List;

public class CreateAccesorieSetAttributesDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private SetAccesorioDTO set;
	private List<String> attributes;

	public CreateAccesorieSetAttributesDTO() {
		super();
	}

	public SetAccesorioDTO getSet() {
		return set;
	}

	public void setSet(SetAccesorioDTO set) {
		this.set = set;
	}

	public List<String> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}
}
