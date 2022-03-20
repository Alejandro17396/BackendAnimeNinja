package com.alejandro.animeninja.bussines.sort.services.impl;

import java.util.Comparator;

import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.BonusAccesorio;
import com.alejandro.animeninja.bussines.model.BonusAccesorioAtributo;
import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.SetAccesorio;

public class SortSetAccesoriosByAttributes implements Comparator <SetAccesorio>{

	private String attribute;
	public SortSetAccesoriosByAttributes(String attribute) {
		this.attribute=attribute;
	}

	@Override
	public int compare(SetAccesorio set1, SetAccesorio set2) {
		BonusAccesorio bonus1=	set1.getBonuses().get(0);
		BonusAccesorio bonus2= 	set2.getBonuses().get(0);
		BonusAccesorioAtributo bonusAtributo1=null;//bonus1.getBonuses().stream().filter(bonus -> bonus.getNombreAtributo().equals(attribute) ).findFirst();
		BonusAccesorioAtributo bonusAtributo2=null;
		
		for(BonusAccesorioAtributo b :bonus1.getBonuses()) {
			if(b.getNombreAtributo().equals(attribute)) {
				bonusAtributo1 = b;
				break;
			}
		}
		for(BonusAccesorioAtributo b :bonus2.getBonuses()) {
			if(b.getNombreAtributo().equals(attribute)) {
				bonusAtributo2 = b;
				break;
			}
		}
		if(bonusAtributo1==null || bonusAtributo2==null) {
			return 0;
		}
		return new SortBonusAccesorioAtributo().compare(bonusAtributo1, bonusAtributo2);
	}

}
