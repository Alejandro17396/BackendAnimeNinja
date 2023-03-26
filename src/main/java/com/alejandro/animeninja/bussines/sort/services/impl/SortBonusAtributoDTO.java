package com.alejandro.animeninja.bussines.sort.services.impl;

import java.util.Comparator;

import com.alejandro.animeninja.bussines.model.dto.BonusAtributoDTO;

public class SortBonusAtributoDTO implements Comparator<BonusAtributoDTO>{

	@Override
	public int compare(BonusAtributoDTO o1, BonusAtributoDTO o2) {
		if(o1.getValor()<o2.getValor()) {
			return -1;
		}else if (o1.getValor()==o2.getValor()) {
			return 0;
		}
		return 1;
	}
}
