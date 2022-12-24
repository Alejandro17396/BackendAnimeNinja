package com.alejandro.animeninja.bussines.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.CreateComboSet;
import com.alejandro.animeninja.bussines.model.dto.SetDTO;

public interface EquipoServices {

	List<Equipo> getAll();

	List<Equipo> getSetsByAttributes(List<Atributo> attributes);
	
	Page <SetDTO> getSetsByAttributes2(CreateComboSet attributes,boolean merge, boolean filter, boolean sorted,Pageable pageable);

	List<Equipo> getSetsBySpecification(Specification<Equipo> specification);

	List<Equipo> generateCombinationSetsByBonus(Specification<Bonus> specification, List<Atributo> attributes,String setName);

	List<Equipo> mergeSetBonus(List<Equipo> equipos);

	void filterSetByStats(List<Equipo> equipos, List<BonusAtributo> attributesFilter);

	void addPartes(List<Equipo> equipos);
	
	Equipo getByNombre(String nombre);
	
	Page <SetDTO> getAllPage(Pageable pageable);
	
	List <SetDTO> generateCombinationSetsByBonus(CreateComboSet attributes,boolean sorted,boolean filtred,String setName, Pageable pageable);
	
	SetDTO createSet(String nombre);
}
