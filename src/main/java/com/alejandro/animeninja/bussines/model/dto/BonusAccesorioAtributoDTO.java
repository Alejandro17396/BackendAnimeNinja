package com.alejandro.animeninja.bussines.model.dto;

import java.util.Objects;

public class BonusAccesorioAtributoDTO {

	private String nombreAtributo;
	private long valor;
	private String action;
	private String impact;
	private String condition;
	private String time;
	private String tipoBonus;
	private String nombreSet;
	
	public String getTipoBonus() {
		return tipoBonus;
	}

	public void setTipoBonus(String tipoBonus) {
		this.tipoBonus = tipoBonus;
	}

	public String getNombreSet() {
		return nombreSet;
	}

	public void setNombreSet(String nombreSet) {
		this.nombreSet = nombreSet;
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

	public BonusAccesorioAtributoDTO(String nombreAtributo, long valor) {
		super();
		this.nombreAtributo = nombreAtributo;
		this.valor = valor;
	}

	public BonusAccesorioAtributoDTO() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombreAtributo, nombreSet, tipoBonus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BonusAccesorioAtributoDTO other = (BonusAccesorioAtributoDTO) obj;
		return Objects.equals(nombreAtributo, other.nombreAtributo) && Objects.equals(nombreSet, other.nombreSet)
				&& Objects.equals(tipoBonus, other.tipoBonus);
	}

	

	
	
}
