package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
	private Formation formation;
	
	@Column(name="sexo")
	@Enumerated(EnumType.STRING)
	private Sex sex;
	
	@Lob
    @Column(name="ninjaImage", columnDefinition="MEDIUMBLOB")
    private byte[] ninjaImage;
	
	@Lob
    @Column(name="ninjaStatImage", columnDefinition="MEDIUMBLOB")
    private byte[] ninjaStatImage;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumns( {
	    @JoinColumn(name="ninja", referencedColumnName="nombre",nullable=false,insertable=false,updatable=false)} )
	
	private List <NinjaStats> stats;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumns( {
	    @JoinColumn(name="ninja", referencedColumnName="nombre",nullable=false,insertable=false,updatable=false)} )

	private List <NinjaSkill> skills;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumns( {
	    @JoinColumn(name="ninja", referencedColumnName="nombre",nullable=false,insertable=false,updatable=false)} )
	
	private List <NinjaAwakening> awakenings;
	
	public Ninja() {
		
	}
	
	public byte[] getNinjaImage() {
		return ninjaImage;
	}

	public void setNinjaImage(byte[] ninjaImage) {
		this.ninjaImage = ninjaImage;
	}

	public byte[] getNinjaStatImage() {
		return ninjaStatImage;
	}

	public void setNinjaStatImage(byte[] ninjaStatImage) {
		this.ninjaStatImage = ninjaStatImage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
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
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
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
		n.setFormation(formation);
		
		return n;
	}

	public Ninja(String name, Formation formation) {
		super();
		this.name = name;
		this.formation = formation;
	}
	
	
	
	
}
