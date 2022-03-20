package com.alejandro.animeninja.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.alejandro.animeninja.bussines.model.BonusAccesorio;
import com.alejandro.animeninja.bussines.model.ClaveBonusAccesorio;

public interface BonusAccesorioRepository extends JpaRepository<BonusAccesorio,ClaveBonusAccesorio>,
												  JpaSpecificationExecutor<BonusAccesorio>{

}
