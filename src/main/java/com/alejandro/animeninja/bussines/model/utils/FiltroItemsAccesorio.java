package com.alejandro.animeninja.bussines.model.utils;

import java.util.List;

import com.alejandro.animeninja.bussines.model.BonusAccesorioAtributo;

public class FiltroItemsAccesorio {
	private BonusAccesorioAtributo bonusAccesorioAtributo;
	private List<String> tiposBonus;

	public FiltroItemsAccesorio(BonusAccesorioAtributo bonusAccesorioAtributo, List<String> tiposBonus) {
		super();
		this.bonusAccesorioAtributo = bonusAccesorioAtributo;
		this.tiposBonus = tiposBonus;
	}

	public FiltroItemsAccesorio() {
		super();
	}

	public BonusAccesorioAtributo getBonusAccesorioAtributo() {
		return bonusAccesorioAtributo;
	}

	public void setBonusAccesorioAtributo(BonusAccesorioAtributo bonusAccesorioAtributo) {
		this.bonusAccesorioAtributo = bonusAccesorioAtributo;
	}

	public List<String> getTiposBonus() {
		return tiposBonus;
	}

	public void setTiposBonus(List<String> tiposBonus) {
		this.tiposBonus = tiposBonus;
	}

}
