package com.alejandro.animeninja.bussines.model.dto;

public class RoleDTO {

	private Long id;
	private String authority;

	public RoleDTO() {
		super();
	}

	public RoleDTO(Long id, String authority) {
		super();
		this.id = id;
		this.authority = authority;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
