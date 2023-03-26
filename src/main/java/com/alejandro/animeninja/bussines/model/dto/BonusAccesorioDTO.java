package com.alejandro.animeninja.bussines.model.dto;

import java.util.List;
import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(bonuses, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BonusAccesorioDTO other = (BonusAccesorioDTO) obj;
		return Objects.equals(bonuses, other.bonuses) && Objects.equals(tipo, other.tipo);
	}

	
}
