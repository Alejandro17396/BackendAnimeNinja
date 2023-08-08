package com.alejandro.animeninja.integration.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alejandro.animeninja.bussines.model.UserAccesories;
import com.alejandro.animeninja.bussines.model.UserSet;

public interface UserAccesoriesRepository extends JpaRepository<UserAccesories, Long>{
	
	Optional <UserAccesories> findByIdAndUsername(Long id,String username);
	Optional <UserAccesories> findByNombreAndUsername(String nombre,String username);
	List <UserAccesories> findByUsername(String user);
	
	@Query("SELECT us FROM UserAccesories us JOIN us.bonuses b WHERE b.tipo = :tipo AND b.nombreAccesorioSet = :nombreAccesorioSet")
    List<UserAccesories> findAllByBonus(@Param("tipo") String tipo, @Param("nombreAccesorioSet") String nombreAccesorioSet);
	
	@Query("SELECT us FROM UserAccesories us JOIN us.partes p WHERE p.nombre = :setName")
    List<UserAccesories> findAllByParte(@Param("setName") String setName);
}
