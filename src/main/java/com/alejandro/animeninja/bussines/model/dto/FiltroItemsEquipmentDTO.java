package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

public class FiltroItemsEquipmentDTO {

	private List<BonusAtributoDTO> bonusAccesorioAtributo;
	private Long numberOfParts;

	public List<BonusAtributoDTO> getBonusAccesorioAtributo() {
		return bonusAccesorioAtributo;
	}

	public void setBonusAccesorioAtributo(List<BonusAtributoDTO> bonusAccesorioAtributo) {
		this.bonusAccesorioAtributo = bonusAccesorioAtributo;
	}

	public Long getNumberOfParts() {
		return numberOfParts;
	}

	public void setNumberOfParts(Long numberOfParts) {
		this.numberOfParts = numberOfParts;
	}

	public FiltroItemsEquipmentDTO(List<BonusAtributoDTO> bonusAccesorioAtributo, Long numberOfParts) {
		super();
		this.bonusAccesorioAtributo = bonusAccesorioAtributo;
		this.numberOfParts = numberOfParts;
	}

	public FiltroItemsEquipmentDTO() {
		super();
	}

}
