package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="NINJA_AWAKENING")
@IdClass(NinjaAwakeningKey.class)
public class NinjaAwakening implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="nombre")
	private String name;
	
	@Id
	@Column(name="tipo")
	@Enumerated(EnumType.STRING)
	private SkillType type;
	
	@Id
	@Column(name="nivel")
	private String level;
	
	@Column(name="activo")
	private boolean active;
	
	@Column(name="ninja")
	private String ninja;
	
	@Column(name="texto")
	private String skillText;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumns( {
	    @JoinColumn(name="nombre", referencedColumnName="nombre",nullable=false,insertable=false,updatable=false),
	    @JoinColumn(name="tipo", referencedColumnName="tipo",nullable=false,insertable=false,updatable=false),
	    @JoinColumn(name="nivel", referencedColumnName="nivel",nullable=false,insertable=false,updatable=false)} )
	private List <NinjaAwakeningStat> stats;
	

	public NinjaAwakening() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SkillType getType() {
		return type;
	}

	public void setType(SkillType type) {
		this.type = type;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getNinja() {
		return ninja;
	}

	public void setNinja(String ninja) {
		this.ninja = ninja;
	}

	public String getSkillText() {
		return skillText;
	}

	public void setSkillText(String skillText) {
		this.skillText = skillText;
	}

	public List<NinjaAwakeningStat> getStats() {
		return stats;
	}

	public void setStats(List<NinjaAwakeningStat> stats) {
		this.stats = stats;
	}
	
	
}
