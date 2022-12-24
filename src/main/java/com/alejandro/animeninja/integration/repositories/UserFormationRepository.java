package com.alejandro.animeninja.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alejandro.animeninja.bussines.model.UserFormation;

public interface UserFormationRepository extends JpaRepository<UserFormation,Long>{

}
