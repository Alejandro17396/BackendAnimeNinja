package com.alejandro.animeninja.integration.repositories;

import java.util.List;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Equipo;

public interface EquipoCustomRepository {
	
	//Page <Equipo> findByListOfAtributtes(List <Atributo> atributos,Pageable pageable);
	List <Equipo> findByListOfAtributtes(List <Atributo> atributos);

}
