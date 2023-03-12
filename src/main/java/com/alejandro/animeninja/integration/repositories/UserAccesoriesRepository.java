package com.alejandro.animeninja.integration.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alejandro.animeninja.bussines.model.UserAccesories;
import com.alejandro.animeninja.bussines.model.UserSet;

public interface UserAccesoriesRepository extends JpaRepository<UserAccesories, Long>{
	
	Optional <UserAccesories> findByIdAndUsername(Long id,String username);
	Optional <UserAccesories> findByNombreAndUsername(String nombre,String username);
}
