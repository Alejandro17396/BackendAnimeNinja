package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

public class SetsDTO {

	private int number;
	private List<SetDTO> sets;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<SetDTO> getSets() {
		return sets;
	}

	public void setSets(List<SetDTO> sets) {
		this.sets = sets;
	}

	public SetsDTO(int number, List<SetDTO> sets) {
		super();
		this.number = number;
		this.sets = sets;
	}

	public SetsDTO() {
		super();
	}

}
