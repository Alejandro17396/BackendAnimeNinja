package com.alejandro.animeninja.integration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alejandro.animeninja.bussines.model.BonusAccesorio;
import com.alejandro.animeninja.bussines.model.ClaveBonusAccesorio;

public interface BonusAccesorioRepository extends JpaRepository<BonusAccesorio,ClaveBonusAccesorio>,
												  JpaSpecificationExecutor<BonusAccesorio>{

	@Query(value = "select * from bonusaccesorios join set_accesorios on set_accesorios.nombre=bonusaccesorios.nombre_set_accesorios right join partes_accesorios on set_accesorios.nombre=partes_accesorios.nombre_set where partes_accesorios.valor>=:valor and partes_accesorios.nombre like '%amulet%';",
			nativeQuery = true)
	List<BonusAccesorio> findBySets(@Param("valor") Long valor);
}
