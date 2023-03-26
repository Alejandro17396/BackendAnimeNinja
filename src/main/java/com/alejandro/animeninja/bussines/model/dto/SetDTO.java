package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;
import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(bonuses, nombre, partes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SetDTO other = (SetDTO) obj;
		return Objects.equals(bonuses, other.bonuses) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(partes, other.partes);
	}
	
	

}
