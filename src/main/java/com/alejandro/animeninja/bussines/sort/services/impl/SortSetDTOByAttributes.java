package com.alejandro.animeninja.bussines.sort.services.impl;

import java.util.Comparator;

import com.alejandro.animeninja.bussines.model.dto.BonusAtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusDTO;
import com.alejandro.animeninja.bussines.model.dto.SetDTO;

public class SortSetDTOByAttributes implements Comparator <SetDTO> {

	private String attribute;
	public SortSetDTOByAttributes(String attribute) {
		this.attribute=attribute;
	}
	
	@Override
	public int compare(SetDTO set1, SetDTO set2) {
		BonusDTO bonus1=set1.getBonuses().get(0);
		BonusDTO bonus2= set2.getBonuses().get(0);
		BonusAtributoDTO bonusAtributo1=null;
		BonusAtributoDTO bonusAtributo2=null;
		
		for(BonusAtributoDTO b :bonus1.getListaBonus()) {
			if(b.getNombreAtributo().equals(attribute)) {
				bonusAtributo1 = b;
				break;
			}
		}
		for(BonusAtributoDTO b :bonus2.getListaBonus()) {
			if(b.getNombreAtributo().equals(attribute)) {
				bonusAtributo2 = b;
				break;
			}
		}
		if(bonusAtributo1==null && bonusAtributo2==null) {
			return 0;
		}
		
		if(bonusAtributo1==null) {
			return -1;
		}
		
		if(bonusAtributo2==null) {
			return 1;
		}
		
		if(bonusAtributo1.getValor()<bonusAtributo2.getValor()) {
			return -1;
		}else if (bonusAtributo1.getValor()==bonusAtributo2.getValor()) {
			return 0;
		}
		return 1;
		
		//return new SortBonusAtributoDTO().compare(bonusAtributo1, bonusAtributo2);
	}

}
