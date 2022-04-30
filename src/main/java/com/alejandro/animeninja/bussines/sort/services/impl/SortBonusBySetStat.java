package com.alejandro.animeninja.bussines.sort.services.impl;

import java.util.Comparator;
import java.util.HashMap;

import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.Equipo;

public class SortBonusBySetStat implements Comparator<Bonus>{
	
	HashMap<String,Equipo> equipos;
	public SortBonusBySetStat(HashMap<String,Equipo> equipos){
		this.equipos=equipos;
		
	}

	@Override
	public int compare(Bonus o1, Bonus o2) {
		// TODO Auto-generated method stub
		Equipo e1= equipos.get(o1.getEquipo());
		Equipo e2= equipos.get(o2.getEquipo());
		
		if(e1.getPartes().get(0).getValor()<e2.getPartes().get(0).getValor()) {
			
			return -1;
		}else if (e1.getPartes().get(0).getValor()==e2.getPartes().get(0).getValor()) {
		
			return 0;
		}
	
		return 1;
	}

}
