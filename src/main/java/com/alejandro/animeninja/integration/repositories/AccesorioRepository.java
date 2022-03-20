package com.alejandro.animeninja.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.alejandro.animeninja.bussines.model.SetAccesorio;

public interface AccesorioRepository extends JpaRepository<SetAccesorio, String>,JpaSpecificationExecutor<SetAccesorio> {

}
