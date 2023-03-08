package com.alejandro.animeninja.bussines.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.BonusAccesorio;
import com.alejandro.animeninja.bussines.model.BonusAccesorioAtributo;
import com.alejandro.animeninja.bussines.model.CreateComboSetAccesorio;
import com.alejandro.animeninja.bussines.model.SetAccesorio;
import com.alejandro.animeninja.bussines.model.dto.SetAccesorioDTO;

public interface AccesorioServices {

	Page <SetAccesorioDTO> getAll(Pageable pageable);

	Page <SetAccesorioDTO> getBySpecification(List<Atributo> attributes,Pageable pageable);

	List<SetAccesorioDTO> getComboAccesoriosBySpecification(CreateComboSetAccesorio attributes,
			boolean sorted,boolean filtred, boolean hardSearch,Pageable pageable);
	
	List<SetAccesorio> getComboAccesoriosBySpecification2(Specification<BonusAccesorio> specification,
			List<Atributo> attributes, boolean hardSearch,String setName);

	List<SetAccesorio> mergeSetBonus(List<SetAccesorio> sets);

	void filterSetByStats(List<SetAccesorio> sets, List<BonusAccesorioAtributo> attributes);

	void addPartes(List<SetAccesorio> sets);

	SetAccesorioDTO getByNombre(String nombre);
	
	SetAccesorio getSetByNombre(String nombre);
	
	SetAccesorio createAccesorieSet(List<String> accesories);

}
