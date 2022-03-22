package com.alejandro.animeninja.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.alejandro.animeninja.bussines.model.Equipo;

public interface EquipoRepository extends JpaRepository<Equipo, String>
			,EquipoCustomRepository,JpaSpecificationExecutor<Equipo> {
	
	
}
