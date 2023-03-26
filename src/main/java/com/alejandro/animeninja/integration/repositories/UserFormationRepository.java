package com.alejandro.animeninja.integration.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alejandro.animeninja.bussines.model.UserFormation;

public interface UserFormationRepository extends JpaRepository<UserFormation,Long>{

	Optional <UserFormation> findByNameAndUser(String name,String user);
	List <UserFormation> findByUser(String user);
}
