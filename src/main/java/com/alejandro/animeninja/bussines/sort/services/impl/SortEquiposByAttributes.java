package com.alejandro.animeninja.bussines.sort.services.impl;

import java.util.Comparator;

import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.Equipo;

public class SortEquiposByAttributes implements Comparator<Equipo> {
	
	private String attribute;
	public SortEquiposByAttributes(String attribute) {
		this.attribute=attribute;
	}

	@Override
	public int compare(Equipo equipo1, Equipo equipo2) {
		Bonus bonus1=equipo1.getBonuses().get(0);
		Bonus bonus2= equipo2.getBonuses().get(0);
		BonusAtributo bonusAtributo1=null;
		BonusAtributo bonusAtributo2=null;
		
		for(BonusAtributo b :bonus1.getListaBonus()) {
			if(b.getNombreAtributo().equals(attribute)) {
				bonusAtributo1 = b;
				break;
			}
		}
		for(BonusAtributo b :bonus2.getListaBonus()) {
			if(b.getNombreAtributo().equals(attribute)) {
				bonusAtributo2 = b;
				break;
			}
		}
		if(bonusAtributo1==null || bonusAtributo2==null) {
			return 0;
		}
		return new SortBonusAtributo().compare(bonusAtributo1, bonusAtributo2);
	}

}
