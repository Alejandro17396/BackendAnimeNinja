package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

public class UserSetDTO {

	private Long id;
	private String nombre;
	private String username;
	private List<ParteDTO> partes;
	private List<BonusDTO> bonuses;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<ParteDTO> getPartes() {
		return partes;
	}

	public void setPartes(List<ParteDTO> partes) {
		this.partes = partes;
	}

	public List<BonusDTO> getBonuses() {
		return bonuses;
	}

	public void setBonuses(List<BonusDTO> bonuses) {
		this.bonuses = bonuses;
	}

	public UserSetDTO() {
		super();
	}

	public UserSetDTO(Long id, String nombre, String username, List<ParteDTO> partes, List<BonusDTO> bonuses) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.username = username;
		this.partes = partes;
		this.bonuses = bonuses;
	}

}
