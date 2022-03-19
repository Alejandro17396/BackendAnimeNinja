package com.alejandro.animeninja.integration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alejandro.animeninja.bussines.model.Parte;

public interface ParteRepository extends JpaRepository<Parte, String> {
	
	List<Parte> findByNombreLike (String cadena);
	List<Parte> findByNombre (String nombre);

}
