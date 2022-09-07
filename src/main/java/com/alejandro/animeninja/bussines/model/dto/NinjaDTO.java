package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;


import com.alejandro.animeninja.bussines.model.ChakraNature;
import com.alejandro.animeninja.bussines.model.Formation;
import com.alejandro.animeninja.bussines.model.NinjaType;

public class NinjaDTO {

	
private String name;
	
	
	private	ChakraNature chakraNature;
	
	private NinjaType type; 

	private Formation Formation;
	
	private List <NinjaStatsDTO> stats;
	
	private List <NinjaSkillDTO> skills;
	
	private List <NinjaAwakeningDTO> awakenings;

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
