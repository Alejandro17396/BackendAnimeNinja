package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

public class UserAccesoriesDTO {

	private Long id;
	private String nombre;
	private String username;
	private List<ParteAccesorioDTO> partes;
	private List<BonusAccesorioDTO> bonuses;

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

	public List<ParteAccesorioDTO> getPartes() {
		return partes;
	}

	public void setPartes(List<ParteAccesorioDTO> partes) {
		this.partes = partes;
	}

	public List<BonusAccesorioDTO> getBonuses() {
		return bonuses;
	}

	public void setBonuses(List<BonusAccesorioDTO> bonuses) {
		this.bonuses = bonuses;
	}

	public UserAccesoriesDTO() {
		super();
	}

	public UserAccesoriesDTO(Long id, String nombre, String username, List<ParteAccesorioDTO> partes,
			List<BonusAccesorioDTO> bonuses) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.username = username;
		this.partes = partes;
		this.bonuses = bonuses;
	}

}
