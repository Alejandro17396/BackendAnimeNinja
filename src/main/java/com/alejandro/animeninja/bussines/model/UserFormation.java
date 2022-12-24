package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="user_formation")
public class UserFormation implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	@Column(name="nombre")
	private String name;
	
	@Column(name="username")
	private String user;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "user_formation_ninja_equipment", 
        joinColumns = { @JoinColumn(name = "formation_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "ninja_id") }
    )
	private List <NinjaEquipment> ninjas;
	
	public UserFormation() {
		
	}

	public UserFormation(Long id, String name, String user, List<NinjaEquipment> ninjas) {
		super();
		this.id = id;
		this.name = name;
		this.user = user;
		this.ninjas = ninjas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<NinjaEquipment> getNinjas() {
		return ninjas;
	}

	public void setNinjas(List<NinjaEquipment> ninjas) {
		this.ninjas = ninjas;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	

}
