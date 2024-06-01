package com.alejandro.animeninja.bussines.services;

import java.util.List;

import com.alejandro.animeninja.bussines.model.BonusAccesorio;
import com.alejandro.animeninja.bussines.model.ParteAccesorio;
import com.alejandro.animeninja.bussines.model.dto.FilterAccesoriePartsDTO;
import com.alejandro.animeninja.bussines.model.dto.FiltroItemsAccesorioDTO;

public interface ParteAccesorioService {

	List<ParteAccesorio> getAll();
	List<ParteAccesorio> getParteAccesorioByBonus(BonusAccesorio bonus);
	ParteAccesorio getById(String name);
	List<ParteAccesorio> findItemsByFilter(FilterAccesoriePartsDTO filtro);
	
}
