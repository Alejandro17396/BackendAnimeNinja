package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

public class NinjaStatsDTO {

	private String level;
	private List<AttributeStatDTO> statsAttributes;


	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public List<AttributeStatDTO> getStatsAttributes() {
		return statsAttributes;
	}

	public void setStatsAttributes(List<AttributeStatDTO> statsAttributes) {
		this.statsAttributes = statsAttributes;
	}

	public NinjaStatsDTO(String level, List<AttributeStatDTO> statsAttributes) {
		super();
		this.level = level;
		this.statsAttributes = statsAttributes;
	}

	public NinjaStatsDTO() {
		super();
	}

}
