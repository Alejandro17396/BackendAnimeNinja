package com.alejandro.animeninja.bussines.services;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.Bonus;

public interface EquipoServices {

	List<Equipo> getAll();

	List<Equipo> getSetsByAttributes(List<Atributo> attributes);

	List<Equipo> getSetsBySpecification(Specification<Equipo> specification);

	List<Equipo> generateCombinationSetsByBonus(Specification<Bonus> specification, List<Atributo> attributes);

	List<Equipo> createSetWithOneBonus(List<Equipo> equipos);
}
