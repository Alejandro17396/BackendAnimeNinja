package com.alejandro.animeninja.bussines.services;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.BonusAtributo;

public interface EquipoServices {

	List<Equipo> getAll();

	List<Equipo> getSetsByAttributes(List<Atributo> attributes);

	List<Equipo> getSetsBySpecification(Specification<Equipo> specification);

	List<Equipo> generateCombinationSetsByBonus(Specification<Bonus> specification, List<Atributo> attributes);

	List<Equipo> mergeSetBonus(List<Equipo> equipos);

	void filterSetByStats(List<Equipo> equipos, List<BonusAtributo> attributesFilter);

	void addPartes(List<Equipo> equipos);
	
	Equipo getByNombre(String nombre);
}
