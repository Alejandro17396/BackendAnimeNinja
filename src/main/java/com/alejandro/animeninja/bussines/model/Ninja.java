package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="NINJAS")
public class Ninja implements Serializable{
   
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "nombre")
	private String name;
	
	@Column(name="chakra_nature")
	@Enumerated(EnumType.STRING)
	private	ChakraNature chakraNature;

	@Column(name="tipo")
	@Enumerated(EnumType.STRING)
	private NinjaType type;

	@Column(name="formacion")
	@Enumerated(EnumType.STRING)
	private Formation Formation;
	
	@OneToMany
	@JoinColumns( {
	    @JoinColumn(name="ninja", nullable = false)} )
	private List <NinjaStats> stats;
	
	@OneToMany
	@JoinColumns( {
	    @JoinColumn(name="ninja", referencedColumnName="nombre",nullable = false)} )
	private List <NinjaSkill> skills;
	
	@OneToMany
	@JoinColumns( {
	    @JoinColumn(name="ninja", referencedColumnName="nombre",nullable = false)} )
	private List <NinjaAwakening> awakenings;
	
	public Ninja() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public ChakraNature getChakraNature() {
		return chakraNature;
	}

	public void setChakraNature(ChakraNature chakraNature) {
		this.chakraNature = chakraNature;
	}

	public NinjaType getType() {
		return type;
	}

	public void setType(NinjaType type) {
		this.type = type;
	}

	public Formation getFormation() {
		return Formation;
	}

	public void setFormation(Formation formation) {
		Formation = formation;
	}
	
	public List<NinjaStats> getStats() {
		return stats;
	}

	public void setStats(List<NinjaStats> stats) {
		this.stats = stats;
	}
	
	public List<NinjaSkill> getSkills() {
		return skills;
	}

	public void setSkills(List<NinjaSkill> skills) {
		this.skills = skills;
	}
	
	public List<NinjaAwakening> getAwakenings() {
		return awakenings;
	}

	public void setAwakenings(List<NinjaAwakening> awakenings) {
		this.awakenings = awakenings;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ninja other = (Ninja) obj;
		return Objects.equals(name, other.name);
	}

	
	public Ninja clone() {
		
		Ninja n = new Ninja();
		n.setName(name);
		n.setChakraNature(chakraNature);
		n.setType(type);
		n.setFormation(Formation);
		
		
		
		return null;
	}
	
	
}
