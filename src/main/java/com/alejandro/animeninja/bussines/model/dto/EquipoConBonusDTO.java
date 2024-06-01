package com.alejandro.animeninja.bussines.model.dto;

import java.util.ArrayList;
import java.util.List;

public class EquipoConBonusDTO {

	SetDTO set;
	List<Integer> partesCumplen;

	public EquipoConBonusDTO() {
		super();
		partesCumplen = new ArrayList<>();
	}

	public SetDTO getSet() {
		return set;
	}

	public void setSet(SetDTO set) {
		this.set = set;
	}

	public List<Integer> getPartesCumplen() {
		return partesCumplen;
	}

	public void setPartesCumplen(List<Integer> partesCumplen) {
		this.partesCumplen = partesCumplen;
	}

}
