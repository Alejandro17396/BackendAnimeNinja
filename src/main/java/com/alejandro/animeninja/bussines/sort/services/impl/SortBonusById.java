package com.alejandro.animeninja.bussines.sort.services.impl;

import java.util.Comparator;

import com.alejandro.animeninja.bussines.model.Bonus;

public class SortBonusById implements Comparator<Bonus> {

	@Override
	public int compare(Bonus b1, Bonus b2) {
		if(b1.getId()<b2.getId()) {
			return -1;
		}else if(b1.getId()== b2.getId()){
			return 0;
		}
		return 1;
	}

}
