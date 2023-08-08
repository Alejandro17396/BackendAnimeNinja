package com.alejandro.animeninja.bussines.model.dto;

import java.io.Serializable;
import java.util.List;

public class CreateSetAttributesDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private SetDTO set;
	private List<String> attributes;

	public CreateSetAttributesDTO() {
		super();
	}

	public SetDTO getSet() {
		return set;
	}

	public void setSet(SetDTO set) {
		this.set = set;
	}

	public List<String> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}

}
