package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

public class SetsAccesorioDTO {

	private int number;
	private List <SetAccesorioDTO> sets;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public List<SetAccesorioDTO> getSets() {
		return sets;
	}
	public void setSets(List<SetAccesorioDTO> sets) {
		this.sets = sets;
	}
	public SetsAccesorioDTO(int number, List<SetAccesorioDTO> sets) {
		super();
		this.number = number;
		this.sets = sets;
	}
	public SetsAccesorioDTO() {
		super();
	}
	
	
	
}