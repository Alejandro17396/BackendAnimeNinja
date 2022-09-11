package com.alejandro.animeninja.bussines.model.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;




public class FormationNinjaDTO {

	private Set<NinjaDTO> supports;
	private Set<NinjaDTO> assaulters;
	private Set<NinjaDTO> vanguards;
	private List<SkillAttributeDTO> mergedAtributes;
	
	public FormationNinjaDTO(Set<NinjaDTO> supports, Set<NinjaDTO> assaulters, Set<NinjaDTO> vanguards,
			List<SkillAttributeDTO> mergedAtributes) {
		super();
		this.supports = supports;
		this.assaulters = assaulters;
		this.vanguards = vanguards;
		this.mergedAtributes = mergedAtributes;
	}

	public FormationNinjaDTO() {
		super();
		supports = new HashSet<NinjaDTO>();
		assaulters = new HashSet<NinjaDTO>();
		vanguards = new HashSet<NinjaDTO>(); 
		mergedAtributes = new ArrayList<SkillAttributeDTO>();
	}
	
	public List<SkillAttributeDTO> getMergedAtributes() {
		return mergedAtributes;
	}

	public void setMergedAtributes(List<SkillAttributeDTO> mergedAtributes) {
		this.mergedAtributes = mergedAtributes;
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
