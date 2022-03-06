package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "BONUSES")
@IdClass(ClaveBonus.class)
public class Bonus implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private Long id;

	@Id
	@Column(name = "nombre_equipo")
	private String equipo;

	@Column(name = "nombre")
	private String nombre;

	@OneToMany
	@JoinColumns({
			// name es el nombre de la columna en la tabla con la que se relaciona es decir
			// "bonus_atributos" y referencedColumn el nombre de la tabla en la misma tabla
			// es decir "bonuses"
			@JoinColumn(name = "nombre_equipo", referencedColumnName = "nombre_equipo"),
			@JoinColumn(name = "id_bonus", referencedColumnName = "id") })
	List<BonusAtributo> listaBonus;

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

	public List<BonusAtributo> getListaBonus() {
		return listaBonus;
	}

	public void setListaBonus(List<BonusAtributo> listaBonus) {
		this.listaBonus = listaBonus;
	}

	@Override
	public String toString() {
		return "Bonus [id=" + id + ", equipo=" + equipo + ", nombre=" + nombre + ", listaBonus=" + listaBonus + "]";
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
		Bonus other = (Bonus) obj;
		return Objects.equals(equipo, other.equipo) && Objects.equals(id, other.id);
	}

}
