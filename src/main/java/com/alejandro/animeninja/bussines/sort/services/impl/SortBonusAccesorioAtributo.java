package com.alejandro.animeninja.bussines.sort.services.impl;

import java.util.Comparator;

import com.alejandro.animeninja.bussines.model.BonusAccesorioAtributo;
import com.alejandro.animeninja.bussines.model.BonusAtributo;

public class SortBonusAccesorioAtributo implements Comparator<BonusAccesorioAtributo>{

	@Override
	public int compare(BonusAccesorioAtributo o1, BonusAccesorioAtributo o2) {
		if(o1.getValor()<o2.getValor()) {
			return -1;
		}else if (o1.getValor()==o2.getValor()) {
			return 0;
		}
		return 1;
	}

}
