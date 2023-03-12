package com.alejandro.animeninja.integration.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alejandro.animeninja.bussines.model.UserSet;

public interface UserSetRepository extends JpaRepository<UserSet,Long>{
	
	Optional <UserSet> findByIdAndUsername(Long id,String username);
	Optional <UserSet> findByNombreAndUsername(String nombre,String username);

}
