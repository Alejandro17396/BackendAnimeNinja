package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

public class CreateAccesorieSetDTO {

	private Long id;
	private List<String> accesories;
	private String accesorieSetName;

	public List<String> getAccesories() {
		return accesories;
	}

	public void setAccesories(List<String> accesories) {
		this.accesories = accesories;
	}

	public String getAccesorieSetName() {
		return accesorieSetName;
	}

	public void setAccesorieSetName(String accesorieSetName) {
		this.accesorieSetName = accesorieSetName;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CreateAccesorieSetDTO() {
		super();
	}

}
