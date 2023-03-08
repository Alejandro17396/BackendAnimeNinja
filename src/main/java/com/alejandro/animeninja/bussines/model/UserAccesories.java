package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="SET_ACCESORIOS_USER")
public class UserAccesories implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="username")
	private String username;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "set_accesorios_user_partes", 
        joinColumns = { @JoinColumn(name = "id_set_accesorio") }, 
        inverseJoinColumns = { @JoinColumn(name = "nombre_parte") }
    )
	private List <ParteAccesorio>  partes;
	
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "set_accesorios_user_bonus", 
        joinColumns = { @JoinColumn(name = "id") }, 
        inverseJoinColumns = { 
            @JoinColumn(name="nombre_set_accesorio_original"),
            @JoinColumn(name="tipo")
         }
    )
	private List <BonusAccesorio> bonuses;

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

	public List<ParteAccesorio> getPartes() {
		return partes;
	}

	public void setPartes(List<ParteAccesorio> partes) {
		this.partes = partes;
	}

	public List<BonusAccesorio> getBonuses() {
		return bonuses;
	}

	public void setBonuses(List<BonusAccesorio> bonuses) {
		this.bonuses = bonuses;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserAccesories() {
		super();
	}

	public UserAccesories(Long id, String nombre, List<ParteAccesorio> partes, List<BonusAccesorio> bonuses) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.partes = partes;
		this.bonuses = bonuses;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAccesories other = (UserAccesories) obj;
		return Objects.equals(id, other.id);
	}

	
	
}
