package com.alejandro.animeninja.bussines.utils;

import java.io.Serializable;
import java.util.Objects;

public class BonusAtributoUtils implements Serializable {

	// atributo,valor,accion,afecta,condicion

	private static final long serialVersionUID = 1L;
	private String attribute;
	private Long value;
	private String action;
	private String impact;
	private String condition;
	private String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
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

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public BonusAtributoUtils(String attribute, Long value, String action, String impact, String condition) {
		super();
		this.attribute = attribute;
		this.value = value;
		this.action = action;
		this.impact = impact;
		this.condition = condition;
	}

	public BonusAtributoUtils() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(action, attribute, condition, impact);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BonusAtributoUtils other = (BonusAtributoUtils) obj;
		return Objects.equals(action, other.action) && Objects.equals(attribute, other.attribute)
				&& Objects.equals(condition, other.condition) && Objects.equals(impact, other.impact);
	}

	
	
}
