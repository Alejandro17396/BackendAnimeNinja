package com.alejandro.animeninja.bussines.sort.services.impl;

import java.util.Comparator;
import java.util.List;

import com.alejandro.animeninja.bussines.model.BonusAccesorio;
import com.alejandro.animeninja.bussines.model.BonusAccesorioAtributo;
import com.alejandro.animeninja.bussines.model.SetAccesorio;
import com.alejandro.animeninja.bussines.model.dto.BonusAccesorioAtributoDTO;
import com.alejandro.animeninja.bussines.model.dto.BonusAccesorioDTO;
import com.alejandro.animeninja.bussines.model.dto.SetAccesorioDTO;

public class SortSetAccesoriosDtoByAttributes implements Comparator <SetAccesorioDTO>{

	private String attribute;
	public SortSetAccesoriosDtoByAttributes(String attribute) {
		this.attribute=attribute;
	}
	
	@Override
	public int compare(SetAccesorioDTO set1, SetAccesorioDTO set2) {
		 if (set1 == null && set2 == null) {
		        return 0;
		    }
		    if (set1 == null) {
		        return -1;
		    }
		    if (set2 == null) {
		        return 1;
		    }
		   
		    List<BonusAccesorioDTO> bonuses1 = set1.getBonuses();
		    List<BonusAccesorioDTO> bonuses2 = set2.getBonuses();
		    if (bonuses1.isEmpty() && bonuses2.isEmpty()) {
		        return 0;
		    }
		    if (bonuses1.isEmpty()) {
		        return -1;
		    }
		    if (bonuses2.isEmpty()) {
		        return 1;
		    }
		    BonusAccesorioAtributoDTO bonusAtributo1 = bonuses1.get(0).getBonuses().stream()
		            .filter(bonus -> bonus.getNombreAtributo().equals(attribute)).findFirst().orElse(null);
		    BonusAccesorioAtributoDTO bonusAtributo2 = bonuses2.get(0).getBonuses().stream()
		            .filter(bonus -> bonus.getNombreAtributo().equals(attribute)).findFirst().orElse(null);
		    /*if (bonusAtributo1 != null && bonusAtributo2 != null) {
		        return Integer.compare((int) bonusAtributo1.getValor(), (int) bonusAtributo2.getValor());
		    }*/
		    
		    if (bonusAtributo1 != null && bonusAtributo2 != null) {
		    	if(bonusAtributo1.equals(bonusAtributo2)) {
			    	return 0;
			    }
		        return Integer.compare((int) bonusAtributo1.getValor(), (int) bonusAtributo2.getValor());
		    }
		    return 0;
	}

}
