package com.alejandro.animeninja.bussines.model;

import java.util.ArrayList;

public class SetAccesorioUtils {

	public static SetAccesorio createSetAccesorio(BonusAccesorio force, BonusAccesorio chakra, BonusAccesorio agi,
			BonusAccesorio power) {
		
		SetAccesorio set = new SetAccesorio();
		set.setBonuses(new ArrayList<>());
		set.getBonuses().add(force);
		set.getBonuses().add(chakra);
		set.getBonuses().add(agi);
		set.getBonuses().add(power);
		createSetAccesorioNombre(set,force,chakra,agi,power);
		
		return set;
	}
	
	public static String createSetAccesorioNombre(SetAccesorio set,BonusAccesorio force, BonusAccesorio chakra, BonusAccesorio agi,
			BonusAccesorio power) {
		
			String nombre="";
		for(BonusAccesorio b: set.getBonuses()) {
			nombre+=b.getNombreAccesorioSet()+" ";
		}
		set.setNombre(nombre);
		
		//nombre+=force.getNombreAccesorioSet()+" "+chakra.getNombreAccesorioSet()+" "+agi.getNombreAccesorioSet()+" "+power.getNombreAccesorioSet();
		
		return nombre;
		
	}

	public static boolean sameBonusSet(SetAccesorio set) {
		String tipo = set.getBonuses().get(0).getTipo();
		for(BonusAccesorio b : set.getBonuses()) {
			if(!b.getTipo().equals(tipo)) {
				return false;
			}
		}
		return true;
	}


	
}
