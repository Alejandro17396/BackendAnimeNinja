package com.alejandro.animeninja.bussines.model.dto;

import java.util.Objects;

import com.alejandro.animeninja.bussines.model.SkillType;

public class NinjaFilterDTO {

	private String attributeName;
	private SkillType type;
	private String action;
	private boolean self;
	private String impact;
	private Long value;
	private String condition;
	private String time;
	private boolean mandatory;
	
	public NinjaFilterDTO() {
		super();
	}

	public NinjaFilterDTO(String attributeName, SkillType type, String action, String impact, Long value,
			String condition, String time) {
		super();
		this.attributeName = attributeName;
		this.type = type;
		this.action = action;
		this.impact = impact;
		this.value = value;
		this.condition = condition;
		this.time = time;
	}
	
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
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
	public boolean isSelf() {
		return self;
	}

	public void setSelf(boolean self) {
		this.self = self;
	}
	
	
	public NinjaFilterDTO clone() {
		NinjaFilterDTO filter = new NinjaFilterDTO();
		filter.action=this.action;
		filter.attributeName=this.attributeName;
		filter.condition=this.condition;
		filter.impact=this.impact;
		filter.self=this.self;
		filter.time=this.time;
		filter.type=this.type;
		filter.value=this.value;
		filter.mandatory=this.mandatory;
		return filter;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	@Override
	public int hashCode() {
		return Objects.hash(action, attributeName, impact, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NinjaFilterDTO other = (NinjaFilterDTO) obj;
		return Objects.equals(action, other.action) && Objects.equals(attributeName, other.attributeName)
				&& Objects.equals(impact, other.impact) && type == other.type;
	}
	
	
	
}
