package com.alejandro.animeninja.bussines.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class SetAccesorioDTO implements Serializable{

	private String nombre;
	private List<ParteAccesorioDTO> partes;
	private List<BonusAccesorioDTO> bonuses;
	private Date fechaSalida;

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

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SetAccesorioDTO other = (SetAccesorioDTO) obj;
		return Objects.equals(nombre, other.nombre);
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	
	

}
