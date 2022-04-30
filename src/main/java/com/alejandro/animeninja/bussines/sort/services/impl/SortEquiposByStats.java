package com.alejandro.animeninja.bussines.sort.services.impl;

import java.util.Comparator;

import com.alejandro.animeninja.bussines.model.Equipo;

public class SortEquiposByStats implements Comparator<Equipo> {

	@Override
	public int compare(Equipo e1, Equipo e2) {
		// TODO Auto-generated method stub
		if(e1.getPartes().get(0).getValor()<e2.getPartes().get(0).getValor()) {
		
			return -1;
		}else if (e1.getPartes().get(0).getValor()==e2.getPartes().get(0).getValor()) {
		
			return 0;
		}
	
		return 1;
	}

}
