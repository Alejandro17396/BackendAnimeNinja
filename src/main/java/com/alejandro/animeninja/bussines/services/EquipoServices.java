package com.alejandro.animeninja.bussines.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.UserSet;
import com.alejandro.animeninja.bussines.model.Bonus;
import com.alejandro.animeninja.bussines.model.BonusAtributo;
import com.alejandro.animeninja.bussines.model.CreateComboSet;
import com.alejandro.animeninja.bussines.model.dto.CreateSetDTO;
import com.alejandro.animeninja.bussines.model.dto.SetDTO;
import com.alejandro.animeninja.bussines.model.dto.UserSetDTO;

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
	
	UserSet createOrUpdateSetByName(CreateSetDTO dto,String user);
	
	UserSet UpdateSetByName(CreateSetDTO dto,String user);
	
	UserSet createSetByName(CreateSetDTO dto,String user);
	
	UserSet createOrUpdateSetById(CreateSetDTO dto, String user);
	
	Equipo createSet(List<String> equipment,String name);
	
	UserSetDTO mergeBonus(UserSet entity);
	
	UserSet getUserSetByName(String username,String name);
	
	boolean deleteUserSetByName(String name,String username);
	
	UserSet saveUserSet(UserSet set);
	
	List <SetDTO> generateCombos(CreateComboSet attributes,boolean sorted,boolean filtred,String setName, Pageable pageable);

	List<UserSetDTO> getNinjasByUser(String user);
	
}
