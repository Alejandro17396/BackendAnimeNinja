package com.alejandro.animeninja.integration.repositories;

import java.util.List;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Equipo;

public interface EquipoCustomRepository {
	
	List <Equipo> findByName(String nombre);
	List <Equipo> findByListOfAtributtes(List <Atributo> atributos);

}
