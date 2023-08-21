package com.alejandro.animeninja.integration.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alejandro.animeninja.bussines.model.SetAccesorio;

public interface AccesorioRepository extends JpaRepository<SetAccesorio, String>,JpaSpecificationExecutor<SetAccesorio> {
	
	 @Query("SELECT s FROM SetAccesorio s WHERE s.fechaSalida BETWEEN :startDate AND :endDate ORDER BY s.fechaSalida DESC")
	 List<SetAccesorio> findBetweenDatesOrderedByFechaSalidaDescLimit(@Param("endDate") Date endDate, @Param("startDate") Date startDate, Pageable pageable);
	 
	 @Query("SELECT s FROM SetAccesorio s WHERE s.fechaSalida BETWEEN :startDate AND :endDate AND s.nombre NOT IN :excludeNames ORDER BY s.fechaSalida DESC")
	 List<SetAccesorio> findBetweenDatesExcludingSetsOrderedByFechaSalidaDesc(
	     @Param("endDate") Date endDate,
	     @Param("startDate") Date startDate,
	     @Param("excludeNames") List<String> excludeNames,
	     Pageable pageable);
	 
	  @Query("SELECT s FROM SetAccesorio s WHERE s.nombre LIKE %:nombreFilter%")
	  Page<SetAccesorio> findByNombreContaining(@Param("nombreFilter") String nombreFilter, Pageable pageable);

	 
	 List<SetAccesorio> findByOrderByFechaSalidaDesc(Pageable pageable);
}
