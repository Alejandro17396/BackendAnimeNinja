package com.alejandro.animeninja.bussines.model.utils;

import java.util.Objects;

import com.alejandro.animeninja.bussines.model.BonusAtributo;

public class BonusAtributoSetsMergeUtils extends BonusAtributo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public int hashCode() {
		return Objects.hash(condition, nombreAtributo, action);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BonusAtributo other = (BonusAtributo) obj;
		return  Objects.equals(nombreAtributo, other.getNombreAtributo())
				&& Objects.equals(action, other.getAction())
				&& Objects.equals(condition, other.getCondition());
	}
}
