package com.alejandro.animeninja.bussines.services;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.BonusAccesorio;
import com.alejandro.animeninja.bussines.model.BonusAccesorioAtributo;
import com.alejandro.animeninja.bussines.model.SetAccesorio;

public interface AccesorioServices2 {

	List<SetAccesorio> getAll();

	List<SetAccesorio> getBySpecification(Specification<SetAccesorio> specification);

	List<SetAccesorio> getComboAccesoriosBySpecification(Specification<BonusAccesorio> specification,
			List<Atributo> attributes, boolean hardSearch,String setName);

	List<SetAccesorio> mergeSetBonus(List<SetAccesorio> sets);

	void filterSetByStats(List<SetAccesorio> sets, List<BonusAccesorioAtributo> attributes);

	void addPartes(List<SetAccesorio> sets);

	SetAccesorio getByNombre(String nombre);

}
