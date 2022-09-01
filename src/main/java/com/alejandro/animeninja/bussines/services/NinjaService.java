package com.alejandro.animeninja.bussines.services;

import java.util.List;

import com.alejandro.animeninja.bussines.model.ClaveNinja;
import com.alejandro.animeninja.bussines.model.Ninja;

public interface NinjaService {

	List <Ninja> getAll();
	
	Ninja getNinja(String clave);
}
