package com.alejandro.animeninja.bussines.model.dto;

import java.util.Objects;

;

public class BonusAtributoDTO {

	private String nombreAtributo;
	private long valor;
	private String action;
	private String impact;
	private String condition;
	private String time;
	private String color;
	private AtributoDTO atributo;
	
	public BonusAtributoDTO(BonusAtributoDTO copy) {
		this.nombreAtributo = copy.nombreAtributo;
		this.action = copy.action;
		this.color = copy.color;
		this.condition = copy.condition;
		this.impact = copy.impact;
		this.time = copy.time;
		this.valor = copy.valor;
		this.atributo = copy.atributo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public BonusAtributoDTO( String nombreAtributo, long valor) {
		super();
		this.nombreAtributo = nombreAtributo;
		this.valor = valor;
	}

	public BonusAtributoDTO() {
		super();
	}

	public String getNombreAtributo() {
		return nombreAtributo;
	}

	public void setNombreAtributo(String nombreAtributo) {
		this.atributo = new AtributoDTO(nombreAtributo);
		this.nombreAtributo = nombreAtributo;
	}
	
	public AtributoDTO getAtributo() {
		return atributo;
	}

	public void setAtributo(AtributoDTO atributo) {
		if(atributo != null) {
			this.nombreAtributo = atributo.getNombre();
		}
		this.atributo = atributo;
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
		return Objects.hash(action, condition, impact, nombreAtributo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BonusAtributoDTO other = (BonusAtributoDTO) obj;
		return Objects.equals(action, other.action) && Objects.equals(condition, other.condition)
				&& Objects.equals(impact, other.impact) && Objects.equals(nombreAtributo, other.nombreAtributo);
	}

	
	
}
