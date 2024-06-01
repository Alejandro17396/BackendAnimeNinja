package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

public class FiltroItemsAccesorioDTO {

	private BonusAccesorioAtributoDTO bonusAccesorioAtributo;
	private List<String> tiposBonus;

	public FiltroItemsAccesorioDTO(BonusAccesorioAtributoDTO bonusAccesorioAtributo, List<String> tiposBonus) {
		super();
		this.bonusAccesorioAtributo = bonusAccesorioAtributo;
		this.tiposBonus = tiposBonus;
	}

	public FiltroItemsAccesorioDTO() {
		super();
	}

	public BonusAccesorioAtributoDTO getBonusAccesorioAtributo() {
		return bonusAccesorioAtributo;
	}

	public void setBonusAccesorioAtributo(BonusAccesorioAtributoDTO bonusAccesorioAtributo) {
		this.bonusAccesorioAtributo = bonusAccesorioAtributo;
	}

	public List<String> getTiposBonus() {
		return tiposBonus;
	}

	public void setTiposBonus(List<String> tiposBonus) {
		this.tiposBonus = tiposBonus;
	}

}
