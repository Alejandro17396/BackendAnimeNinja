package com.alejandro.animeninja.integration.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alejandro.animeninja.bussines.model.NinjaUserFormation;
import com.alejandro.animeninja.bussines.model.UserFormation;

public interface UserFormationRepository extends JpaRepository<UserFormation,Long>{

	Optional <UserFormation> findByNameAndUser(String name,String user);
	List <UserFormation> findByUser(String user);
	
	@Query("SELECT uf FROM UserFormation uf JOIN uf.ninjas nuf JOIN nuf.ninja n WHERE n.name = :name")
	List<UserFormation> findAllByNinjaName(@Param("name") String name);

}
