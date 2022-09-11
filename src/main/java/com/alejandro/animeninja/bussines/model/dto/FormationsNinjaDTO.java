package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

public class FormationsNinjaDTO {

	private int numFormations;
	private List<FormationNinjaDTO> formations;

	public FormationsNinjaDTO() {
		super();
	}

	public FormationsNinjaDTO(int numFormations, List<FormationNinjaDTO> formations) {
		super();
		this.numFormations = numFormations;
		this.formations = formations;
	}

	public int getNumFormations() {
		return numFormations;
	}

	public void setNumFormations(int numFormations) {
		this.numFormations = numFormations;
	}

	public List<FormationNinjaDTO> getFormations() {
		return formations;
	}

	public void setFormations(List<FormationNinjaDTO> formations) {
		this.formations = formations;
	}

}
