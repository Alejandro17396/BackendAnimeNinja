package com.alejandro.animeninja.bussines.sort.services.impl;

import java.util.Comparator;

import com.alejandro.animeninja.bussines.model.BonusAtributo;

public class SortBonusAtributo implements Comparator<BonusAtributo>{

	@Override
	public int compare(BonusAtributo o1, BonusAtributo o2) {
		if(o1.getValor()<o2.getValor()) {
			return -1;
		}else if (o1.getValor()==o2.getValor()) {
			return 0;
		}
		return 1;
	}

}
