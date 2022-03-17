package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.Objects;



public class ClaveBonus implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String equipo;
	public Long getId() {
		return id;
	}
	public ClaveBonus() {

	}
	public ClaveBonus(Long id, String equipo) {
		super();
		this.id = id;
		this.equipo = equipo;
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
	@Override
	public String toString() {
		return "ClaveBonus [id=" + id + ", equipo=" + equipo + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(equipo, id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClaveBonus other = (ClaveBonus) obj;
		return Objects.equals(equipo, other.equipo) && Objects.equals(id, other.id);
	}
	
	
	
	
}
