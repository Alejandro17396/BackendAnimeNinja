package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;


import com.alejandro.animeninja.bussines.model.ChakraNature;
import com.alejandro.animeninja.bussines.model.Formation;
import com.alejandro.animeninja.bussines.model.NinjaType;
import com.alejandro.animeninja.bussines.model.Sex;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NinjaDTO {

	private String name;
	
	private	ChakraNature chakraNature;
	
	private NinjaType type; 

	private Formation Formation;
	
	private Sex sex;
	
	private List <NinjaStatsDTO> stats;
	
	private List <NinjaSkillDTO> skills;
	
	private List <NinjaAwakeningDTO> awakenings;
	
	@JsonProperty 
	private byte[] ninjaImage;
	
	@JsonProperty 
	private byte[] ninjaStatImage;

	public byte[] getNinjaImage() {
		return ninjaImage;
	}

	@JsonIgnore
	public void setNinjaImage(byte[] ninjaImage) {
		this.ninjaImage = ninjaImage;
	}

	public byte[] getNinjaStatImage() {
		return ninjaStatImage;
	}

	@JsonIgnore
	public void setNinjaStatImage(byte[] ninjaStatImage) {
		this.ninjaStatImage = ninjaStatImage;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
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

	public List<NinjaStatsDTO> getStats() {
		return stats;
	}

	public void setStats(List<NinjaStatsDTO> stats) {
		this.stats = stats;
	}

	public List<NinjaSkillDTO> getSkills() {
		return skills;
	}

	public void setSkills(List<NinjaSkillDTO> skills) {
		this.skills = skills;
	}

	public List<NinjaAwakeningDTO> getAwakenings() {
		return awakenings;
	}

	public void setAwakenings(List<NinjaAwakeningDTO> awakenings) {
		this.awakenings = awakenings;
	}

	public NinjaDTO(String name, ChakraNature chakraNature, NinjaType type,
			Formation formation, List<NinjaStatsDTO> stats,
			List<NinjaSkillDTO> skills, List<NinjaAwakeningDTO> awakenings) {
		super();
		this.name = name;
		this.chakraNature = chakraNature;
		this.type = type;
		Formation = formation;
		this.stats = stats;
		this.skills = skills;
		this.awakenings = awakenings;
	}

	public NinjaDTO() {
		super();
	}
	
	
	
}
