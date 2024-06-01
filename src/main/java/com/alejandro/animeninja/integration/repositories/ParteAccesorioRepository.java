package com.alejandro.animeninja.integration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.alejandro.animeninja.bussines.model.BonusAccesorio;
import com.alejandro.animeninja.bussines.model.ParteAccesorio;

public interface ParteAccesorioRepository extends JpaRepository<ParteAccesorio, String>,
													JpaSpecificationExecutor<ParteAccesorio>{
	
	List<ParteAccesorio> findByNombreSetAndTipo(String nombreSet,String tipo);

}
