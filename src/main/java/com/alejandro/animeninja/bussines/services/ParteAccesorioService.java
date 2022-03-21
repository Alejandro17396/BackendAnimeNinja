package com.alejandro.animeninja.bussines.services;

import java.util.List;

import com.alejandro.animeninja.bussines.model.BonusAccesorio;
import com.alejandro.animeninja.bussines.model.Parte;
import com.alejandro.animeninja.bussines.model.ParteAccesorio;

public interface ParteAccesorioService {

	List<ParteAccesorio> getAll();
	List<ParteAccesorio> getParteAccesorioByBonus(BonusAccesorio bonus);
}
