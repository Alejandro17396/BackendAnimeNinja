package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;


public class UsuarioDTO {


	private String username;
	private String password;
	private Boolean enabled;
	private String mail;
	private List<RoleDTO> roles;

	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO( String username, String password, Boolean enabled, String mail, List<RoleDTO> roles) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.mail = mail;
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

}
