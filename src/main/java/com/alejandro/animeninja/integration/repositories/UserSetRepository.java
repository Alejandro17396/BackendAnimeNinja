package com.alejandro.animeninja.integration.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alejandro.animeninja.bussines.model.UserSet;

public interface UserSetRepository extends JpaRepository<UserSet,Long>{
	
	Optional <UserSet> findByIdAndUsername(Long id,String username);
	Optional <UserSet> findByNombreAndUsername(String nombre,String username);
	List <UserSet> findByUsername(String username);

	@Query("SELECT us FROM UserSet us JOIN us.bonuses b WHERE b.id = :bonusId AND b.equipo = :bonusEquipo")
    List<UserSet> findAllByBonus(@Param("bonusId") Long bonusId, @Param("bonusEquipo") String bonusEquipo);
	
	@Query("SELECT us FROM UserSet us JOIN us.partes p WHERE p.equipo = :equipo")
    List<UserSet> findAllByParte(@Param("equipo") String equipo);
}