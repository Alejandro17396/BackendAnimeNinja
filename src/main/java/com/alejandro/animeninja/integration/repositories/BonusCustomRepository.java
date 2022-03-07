package com.alejandro.animeninja.integration.repositories;

import java.util.List;

import com.alejandro.animeninja.bussines.model.Atributo;
import com.alejandro.animeninja.bussines.model.Bonus;


public interface BonusCustomRepository {

	List <Bonus> findByListOfAtributtes(List <Atributo> atributos);

}
