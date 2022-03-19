package com.alejandro.animeninja.bussines.sort.services.impl;

import java.util.Comparator;

import com.alejandro.animeninja.bussines.model.Parte;

public class SortPartes implements Comparator <Parte>{

	@Override
	public int compare(Parte o1, Parte o2) {
		if(o1.getValor() < o2.getValor()) {
			return -1;
		}else if(o1.getValor() == o2.getValor()) {
			return 0;
		}
		return 1;
	}

}
