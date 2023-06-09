package com.alejandro.animeninja.bussines.model.dto;

public class CompareFormationsDTO {

	private FormationNinjaDTO formationLeft;
	private FormationNinjaDTO formationRight;

	public CompareFormationsDTO() {
		super();
	}

	public FormationNinjaDTO getFormationLeft() {
		return formationLeft;
	}

	public void setFormationLeft(FormationNinjaDTO formationLeft) {
		this.formationLeft = formationLeft;
	}

	public FormationNinjaDTO getFormationRight() {
		return formationRight;
	}

	public void setFormationRight(FormationNinjaDTO formationRight) {
		this.formationRight = formationRight;
	}

}
