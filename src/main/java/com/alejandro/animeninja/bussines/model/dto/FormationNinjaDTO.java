package com.alejandro.animeninja.bussines.model.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;




public class FormationNinjaDTO {

	private String formationNinjas;
	private Set<NinjaDTO> supports;
	private Set<NinjaDTO> assaulters;
	private Set<NinjaDTO> vanguards;
	private List<SkillAttributeDTO> mergedTalentAttributes;
	private List<SkillAttributeDTO> finalSkillAttributes;
	
	public FormationNinjaDTO(Set<NinjaDTO> supports, Set<NinjaDTO> assaulters, Set<NinjaDTO> vanguards,
			List<SkillAttributeDTO> mergedAtributes) {
		super();
		this.supports = supports;
		this.assaulters = assaulters;
		this.vanguards = vanguards;
		this.mergedTalentAttributes = mergedAtributes;
	}

	public FormationNinjaDTO() {
		super();
		supports = new HashSet<NinjaDTO>();
		assaulters = new HashSet<NinjaDTO>();
		vanguards = new HashSet<NinjaDTO>(); 
		mergedTalentAttributes = new ArrayList<SkillAttributeDTO>();
	}

	public List<SkillAttributeDTO> getMergedTalentAttributes() {
		return mergedTalentAttributes;
	}

	public void setMergedTalentAttributes(List<SkillAttributeDTO> mergedTalentAttributes) {
		this.mergedTalentAttributes = mergedTalentAttributes;
	}

	public List<SkillAttributeDTO> getFinalSkillAttributes() {
		return finalSkillAttributes;
	}

	public void setFinalSkillAttributes(List<SkillAttributeDTO> finalSkillAttributes) {
		this.finalSkillAttributes = finalSkillAttributes;
	}

	public String getFormationNinjas() {
		return formationNinjas;
	}

	public void setFormationNinjas(String formationNinjas) {
		this.formationNinjas = formationNinjas;
	}

	public Set<NinjaDTO> getSupports() {
		return supports;
	}

	public void setSupports(Set<NinjaDTO> supports) {
		this.supports = supports;
	}

	public Set<NinjaDTO> getAssaulters() {
		return assaulters;
	}

	public void setAssaulters(Set<NinjaDTO> assaulters) {
		this.assaulters = assaulters;
	}

	public Set<NinjaDTO> getVanguards() {
		return vanguards;
	}

	public void setVanguards(Set<NinjaDTO> vanguards) {
		this.vanguards = vanguards;
	}

	@Override
	public int hashCode() {
		return Objects.hash(assaulters, supports, vanguards);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormationNinjaDTO other = (FormationNinjaDTO) obj;
		return Objects.equals(assaulters, other.assaulters) && Objects.equals(supports, other.supports)
				&& Objects.equals(vanguards, other.vanguards);
	}
	

}
