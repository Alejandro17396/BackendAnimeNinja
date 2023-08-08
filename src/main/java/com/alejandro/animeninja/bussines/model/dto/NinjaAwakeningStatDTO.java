package com.alejandro.animeninja.bussines.model.dto;

import com.alejandro.animeninja.bussines.model.SkillType;

public class NinjaAwakeningStatDTO {
	
	

	private String name;
	private String ninja;
	private String level;
	private String attributeName;
	private SkillType type;
	private String action;
	private String impact;
	private Long value;
	private String condition;
	private AtributoDTO atributo;
	private String time;
	
	
	
	public NinjaAwakeningStatDTO() {
		super();
	}
	public NinjaAwakeningStatDTO(String name, String ninja, String level, String attributeName, SkillType type,
			String action, String impact, Long value, String condition, String time) {
		super();
		this.name = name;
		this.ninja = ninja;
		this.level = level;
		this.attributeName = attributeName;
		this.type = type;
		this.action = action;
		this.impact = impact;
		this.value = value;
		this.condition = condition;
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNinja() {
		return ninja;
	}
	public void setNinja(String ninja) {
		this.ninja = ninja;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.atributo = new AtributoDTO(attributeName);
		this.attributeName = attributeName;
	}
	
	public AtributoDTO getAtributo() {
		return atributo;
	}
	public void setAtributo(AtributoDTO atributo) {
		if(atributo != null) {
			this.attributeName = atributo.getNombre();
		}
		this.atributo = atributo;
	}
	public SkillType getType() {
		return type;
	}
	public void setType(SkillType type) {
		this.type = type;
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
	
	
	
}
