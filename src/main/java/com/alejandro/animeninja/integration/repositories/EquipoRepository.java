package com.alejandro.animeninja.integration.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alejandro.animeninja.bussines.model.Equipo;
import com.alejandro.animeninja.bussines.model.SetAccesorio;

public interface EquipoRepository extends JpaRepository<Equipo, String>
			,EquipoCustomRepository,JpaSpecificationExecutor<Equipo> {
	
	@Query("SELECT s FROM Equipo s WHERE s.nombre LIKE %:nombreFilter%")
	  Page<Equipo> findByNombreContaining(@Param("nombreFilter") String nombreFilter, Pageable pageable);

	
}
