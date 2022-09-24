package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

public class SetDTO {

	private String nombre;
	private List<ParteDTO> partes;
	private List<BonusDTO> bonuses;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public SetDTO(String nombre, List<ParteDTO> partes, List<BonusDTO> bonuses) {
		super();
		this.nombre = nombre;
		this.partes = partes;
		this.bonuses = bonuses;
	}

	public SetDTO() {
		super();
	}

}
