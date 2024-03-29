package com.alejandro.animeninja.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alejandro.animeninja.bussines.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>{

}
