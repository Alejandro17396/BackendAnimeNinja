package com.alejandro.animeninja.bussines.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="NINJA_STATS")
@IdClass(NinjaStatsKey.class)
public class NinjaStats implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ninja" ,insertable=false, updatable=false)
	private String name;
	
	@Id
	@Column(name="nivel")
	private String level;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumns( {
	    @JoinColumn(name="ninja",referencedColumnName="ninja",nullable=false,insertable=false,updatable=false),
	    @JoinColumn(name="nivel",referencedColumnName="nivel",nullable=false,insertable=false,updatable=false)} )
	//@Transient
	private List <AttributeStat> statsAttributes;
	
	public NinjaStats() {
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLevel() {
		return level;
	}


	public void setLevel(String level) {
		this.level = level;
	}


	public List<AttributeStat> getStatsAttributes() {
		return statsAttributes;
	}


	public void setStatsAttributes(List<AttributeStat> statsAttributes) {
		this.statsAttributes = statsAttributes;
	}
	
	
}
