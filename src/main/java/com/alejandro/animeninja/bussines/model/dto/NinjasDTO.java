package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

public class NinjasDTO {

	private int number;
	private List<NinjaDTO> ninjas;
	
	
	public NinjasDTO() {
		super();
	}
	public NinjasDTO(int number, List<NinjaDTO> ninjas) {
		super();
		this.number = number;
		this.ninjas = ninjas;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public List<NinjaDTO> getNinjas() {
		return ninjas;
	}
	public void setNinjas(List<NinjaDTO> ninjas) {
		this.ninjas = ninjas;
	}
	
	
	
	
}
