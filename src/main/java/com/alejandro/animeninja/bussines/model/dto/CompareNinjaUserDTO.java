package com.alejandro.animeninja.bussines.model.dto;

import com.alejandro.animeninja.bussines.model.NinjaUserFormation;

public class CompareNinjaUserDTO {

	private NinjaUserFormationDTO ninjaLeft;
	private NinjaUserFormationDTO ninjaRight;

	public CompareNinjaUserDTO(NinjaUserFormationDTO ninjaLeft, NinjaUserFormationDTO ninjaRight) {
		super();
		this.ninjaLeft = ninjaLeft;
		this.ninjaRight = ninjaRight;
	}

	public CompareNinjaUserDTO() {
		super();
	}

	public NinjaUserFormationDTO getNinjaLeft() {
		return ninjaLeft;
	}

	public void setNinjaLeft(NinjaUserFormationDTO ninjaLeft) {
		this.ninjaLeft = ninjaLeft;
	}

	public NinjaUserFormationDTO getNinjaRight() {
		return ninjaRight;
	}

	public void setNinjaRight(NinjaUserFormationDTO ninjaRight) {
		this.ninjaRight = ninjaRight;
	}

}
