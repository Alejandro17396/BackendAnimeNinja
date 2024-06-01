package com.alejandro.animeninja.bussines.model.utils;

import java.util.List;

import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.dto.BonusAtributoDTO;

public class FiltroItemsEquipment {

	private List<BonusAtributo> bonusAccesorioAtributo;
	private Long numberOfParts;

	public List<BonusAtributo> getBonusAccesorioAtributo() {
		return bonusAccesorioAtributo;
	}

	public void setBonusAccesorioAtributo(List<BonusAtributo> bonusAccesorioAtributo) {
		this.bonusAccesorioAtributo = bonusAccesorioAtributo;
	}

	public Long getNumberOfParts() {
		return numberOfParts;
	}

	public void setNumberOfParts(Long numberOfParts) {
		this.numberOfParts = numberOfParts;
	}

	public FiltroItemsEquipment(List<BonusAtributo> bonusAccesorioAtributo, Long numberOfParts) {
		super();
		this.bonusAccesorioAtributo = bonusAccesorioAtributo;
		this.numberOfParts = numberOfParts;
	}

	public FiltroItemsEquipment() {
		super();
	}

}
