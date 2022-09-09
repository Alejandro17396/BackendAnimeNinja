package com.alejandro.animeninja.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.alejandro.animeninja.bussines.model.Ninja;

@Repository
public interface NinjaRepository extends JpaRepository<Ninja, String> ,JpaSpecificationExecutor<Ninja>{

}
