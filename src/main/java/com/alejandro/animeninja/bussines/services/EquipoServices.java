package com.alejandro.animeninja.bussines.services;

import java.util.List;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Equipo;



public interface EquipoServices {

	List <Equipo> getAll();
	List <Equipo> getSetsByAttributes(List <Atributo> attributes);
}
