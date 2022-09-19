package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

public class BonusDTO {

	private Long id;
	private String equipo;
	private String nombre;
	List<BonusAtributoDTO> listaBonus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

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

	public BonusDTO(Long id, String equipo, String nombre, List<BonusAtributoDTO> listaBonus) {
		super();
		this.id = id;
		this.equipo = equipo;
		this.nombre = nombre;
		this.listaBonus = listaBonus;
	}

	public BonusDTO() {
		super();
	}

}
