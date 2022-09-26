package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

public class SetAccesorioDTO {

	private String nombre;
	private List<ParteAccesorioDTO> partes;
	private List<BonusAccesorioDTO> bonuses;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	public SetAccesorioDTO(String nombre, List<ParteAccesorioDTO> partes, List<BonusAccesorioDTO> bonuses) {
		super();
		this.nombre = nombre;
		this.partes = partes;
		this.bonuses = bonuses;
	}

	public SetAccesorioDTO() {
		super();
	}

}
