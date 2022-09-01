package com.alejandro.animeninja.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alejandro.animeninja.bussines.model.AttributeStat;
import com.alejandro.animeninja.bussines.model.AttributeStatKey;

public interface AttributeStatRepository extends JpaRepository<AttributeStat, AttributeStatKey>{

}
