package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;
import java.util.Objects;

public class BonusDTO {

	private String nombre;
	private List<BonusAtributoDTO> listaBonus;

	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<BonusAtributoDTO> getListaBonus() {
		return listaBonus;
	}

	public void setListaBonus(List<BonusAtributoDTO> listaBonus) {
		this.listaBonus = listaBonus;
	}

	public BonusDTO(String nombre, List<BonusAtributoDTO> listaBonus) {
		super();
		this.nombre = nombre;
		this.listaBonus = listaBonus;
	}

	public BonusDTO() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(listaBonus, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BonusDTO other = (BonusDTO) obj;
		return Objects.equals(listaBonus, other.listaBonus) && Objects.equals(nombre, other.nombre);
	}
	

}
