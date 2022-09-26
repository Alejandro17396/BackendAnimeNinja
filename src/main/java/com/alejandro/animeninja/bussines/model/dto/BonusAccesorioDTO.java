package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;

public class BonusAccesorioDTO {

	private String tipo;
	private List<BonusAccesorioAtributoDTO> bonuses;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<BonusAccesorioAtributoDTO> getBonuses() {
		return bonuses;
	}

	public void setBonuses(List<BonusAccesorioAtributoDTO> bonuses) {
		this.bonuses = bonuses;
	}

	public BonusAccesorioDTO(String tipo, List<BonusAccesorioAtributoDTO> bonuses) {
		super();
		this.tipo = tipo;
		this.bonuses = bonuses;
	}

	public BonusAccesorioDTO() {
		super();
	}

}
