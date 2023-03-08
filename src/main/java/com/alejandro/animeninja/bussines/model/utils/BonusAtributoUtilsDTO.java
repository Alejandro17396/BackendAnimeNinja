package com.alejandro.animeninja.bussines.model.utils;

import java.util.Objects;

public class BonusAtributoUtilsDTO {

	private String nombreAtributo;
	private long valor;
	private String action;
	private String impact;
	private String condition;
	private String time;
	public String getNombreAtributo() {
		return nombreAtributo;
	}
	public void setNombreAtributo(String nombreAtributo) {
		this.nombreAtributo = nombreAtributo;
	}
	public long getValor() {
		return valor;
	}
	public void setValor(long valor) {
		this.valor = valor;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public int hashCode() {
		return Objects.hash(action, condition, nombreAtributo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BonusAtributoUtilsDTO other = (BonusAtributoUtilsDTO) obj;
		return Objects.equals(action, other.action) && Objects.equals(condition, other.condition)
				&& Objects.equals(nombreAtributo, other.nombreAtributo);
	}
	public BonusAtributoUtilsDTO() {
		super();
	}
	
	public BonusAtributoUtilsDTO deepCopy() {
		BonusAtributoUtilsDTO element = new BonusAtributoUtilsDTO();
		
		element.setAction(action);
		element.setCondition(condition);
		element.setImpact(impact);
		element.setNombreAtributo(nombreAtributo);
		element.setTime(time);
		element.setValor(valor);
		
		return element;
	}
	
}
