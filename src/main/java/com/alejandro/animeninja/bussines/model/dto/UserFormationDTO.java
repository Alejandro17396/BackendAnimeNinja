package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

public class UserFormationDTO {

	private Long id;
	private String name;
	private String user;
	private List<NinjaUserFormationDTO> ninjas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<NinjaUserFormationDTO> getNinjas() {
		return ninjas;
	}

	public void setNinjas(List<NinjaUserFormationDTO> ninjas) {
		this.ninjas = ninjas;
	}

	public UserFormationDTO() {
		super();
	}

	public UserFormationDTO(Long id, String name, String user, List<NinjaUserFormationDTO> ninjas) {
		super();
		this.id = id;
		this.name = name;
		this.user = user;
		this.ninjas = ninjas;
	}

}
