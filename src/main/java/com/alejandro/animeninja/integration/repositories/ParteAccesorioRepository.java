package com.alejandro.animeninja.integration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alejandro.animeninja.bussines.model.ParteAccesorio;

public interface ParteAccesorioRepository extends JpaRepository<ParteAccesorio, String>{
	
	List<ParteAccesorio> findByNombreSetAndTipo(String nombreSet,String tipo);

}
