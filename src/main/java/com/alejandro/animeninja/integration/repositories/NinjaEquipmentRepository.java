package com.alejandro.animeninja.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alejandro.animeninja.bussines.model.NinjaUserFormation;

@Repository
public interface NinjaEquipmentRepository extends JpaRepository <NinjaUserFormation,Long>{

}
