package com.alejandro.animeninja.bussines.model.dto;

public class RoleDTO {

	private String authority;

	public RoleDTO() {
		super();
	}

	public RoleDTO(String authority) {
		super();
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
