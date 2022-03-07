package com.alejandro.animeninja.bussines.services;

import java.util.List;

import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.Atributo;


public interface BonusServices {

	List<Bonus> getAll();
	List<Bonus> getBonusByAttributes(List <Atributo> atributos);
}
