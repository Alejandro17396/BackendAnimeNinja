package com.alejandro.animeninja.bussines.model.dto;


public class SkillAttributeDTO {

	
	private String attributeName;
	private String action;
	private String impact;
	private Long value;
	private String condition;
	private String time;
	private String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getImpact() {
		return impact;
	}

	public void setImpact(String impact) {
		this.impact = impact;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public SkillAttributeDTO() {
		super();
	}
	
	public SkillAttributeDTO(SkillAttributeDTO copy) {
		this.attributeName = copy.attributeName;
		this.action = copy.action;
		this.color = copy.color;
		this.condition = copy.condition;
		this.impact = copy.impact;
		this.time = copy.time;
		this.value = copy.value;
	}

	public SkillAttributeDTO( String attributeName, String action,String impact, Long value, String condition, String time) {
		super();
		this.attributeName = attributeName;
		this.action = action;
		this.impact = impact;
		this.value = value;
		this.condition = condition;
		this.time = time;
	}

	@Override
	public String toString() {
		return "SkillAttributeDTO [attributeName=" + attributeName + ", action=" + action + ", impact=" + impact
				+ ", condition=" + condition + "]";
	}
	
	
	
	

}
