package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

public class BonusDTO {

	private String nombre;
	List<BonusAtributoDTO> listaBonus;

	

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

}
