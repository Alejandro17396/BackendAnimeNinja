package com.alejandro.animeninja.bussines.sort.services.impl;

import java.util.Comparator;
import java.util.List;

import com.alejandro.animeninja.bussines.model.BonusAccesorio;
import com.alejandro.animeninja.bussines.model.BonusAccesorioAtributo;
import com.alejandro.animeninja.bussines.model.SetAccesorio;

public class SortSetAccesoriosByAttributes implements Comparator <SetAccesorio>{

	private String attribute;
	public SortSetAccesoriosByAttributes(String attribute) {
		this.attribute=attribute;
	}

	@Override
	public int compare(SetAccesorio set1, SetAccesorio set2) {
/*		BonusAccesorio bonus1=	set1.getBonuses().get(0);
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
		
		
		if(bonusAtributo1!=null && bonusAtributo2!=null) {
			if(bonusAtributo1.getValor()<bonusAtributo2.getValor()) {
				return -1;
			}else if (bonusAtributo1.getValor()>bonusAtributo2.getValor()) {
				return 1;
			}
		}
		return 0;
		
		
		
*/
		 if (set1 == null && set2 == null) {
		        return 0;
		    }
		    if (set1 == null) {
		        return -1;
		    }
		    if (set2 == null) {
		        return 1;
		    }
		    List<BonusAccesorio> bonuses1 = set1.getBonuses();
		    List<BonusAccesorio> bonuses2 = set2.getBonuses();
		    if (bonuses1.isEmpty() && bonuses2.isEmpty()) {
		        return 0;
		    }
		    if (bonuses1.isEmpty()) {
		        return -1;
		    }
		    if (bonuses2.isEmpty()) {
		        return 1;
		    }
		    BonusAccesorioAtributo bonusAtributo1 = bonuses1.get(0).getBonuses().stream()
		            .filter(bonus -> bonus.getNombreAtributo().equals(attribute)).findFirst().orElse(null);
		    BonusAccesorioAtributo bonusAtributo2 = bonuses2.get(0).getBonuses().stream()
		            .filter(bonus -> bonus.getNombreAtributo().equals(attribute)).findFirst().orElse(null);
		    if (bonusAtributo1 != null && bonusAtributo2 != null) {
		        return Integer.compare((int) bonusAtributo1.getValor(), (int) bonusAtributo2.getValor());
		    }
		    return 0;

		
	}

}
