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
@Table(name="formation_user")
public class UserFormation implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="nombre")
	private String name;
	
	@Column(name="username")
	private String user;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "formation_user_ninja_user", 
        joinColumns = { @JoinColumn(name = "formation_user_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "ninja_user_id") }
    )
	private List <NinjaUserFormation> ninjas;
	
	public UserFormation() {
		
	}

	public UserFormation(Long id, String name, String user, List<NinjaUserFormation> ninjas) {
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

	public List<NinjaUserFormation> getNinjas() {
		return ninjas;
	}

	public void setNinjas(List<NinjaUserFormation> ninjas) {
		this.ninjas = ninjas;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	

}
