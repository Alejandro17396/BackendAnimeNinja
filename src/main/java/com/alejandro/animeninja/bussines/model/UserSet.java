package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.List;

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
@Table(name="EQUIPOS_USER")
public class UserSet implements Serializable{
	
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
        name = "equipos_user_partes", 
        joinColumns = { @JoinColumn(name = "id") }, 
        inverseJoinColumns = { @JoinColumn(name = "nombre_parte") }
    )
	private List <Parte> partes;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "equipos_user_bonus", 
        joinColumns = { @JoinColumn(name = "id") }, 
        inverseJoinColumns = { 
            @JoinColumn(name="nombre_equipo_original"),
            @JoinColumn(name="id_bonus")
         }
    )
	private List <Bonus> bonuses;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Parte> getPartes() {
		return partes;
	}

	public void setPartes(List<Parte> partes) {
		this.partes = partes;
	}

	public List<Bonus> getBonuses() {
		return bonuses;
	}

	public void setBonuses(List<Bonus> bonuses) {
		this.bonuses = bonuses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserSet() {
		super();
	}

	public UserSet(String nombre, String username, List<Parte> partes, List<Bonus> bonuses) {
		super();
		this.nombre = nombre;
		this.username = username;
		this.partes = partes;
		this.bonuses = bonuses;
	}
	
	
	
}
