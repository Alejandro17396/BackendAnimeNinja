package com.alejandro.animeninja.bussines.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.dto.CreateComboNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.FormationNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaDTO;

public interface NinjaService {

	List <Ninja> getAll();
	
	List <Ninja> getNinjasBySpecification(Specification <Ninja> specification,Pageable pageable);
	
	Ninja getNinja(String clave) throws InterruptedException;
	
	List<FormationNinjaDTO> getNinjaComboFormations(CreateComboNinjaDTO attributes,boolean merge,boolean sorted,boolean filtred,
									boolean or,boolean awakenings);
	
	Page <NinjaDTO> getNinjaFiltroAnd(CreateComboNinjaDTO attributes, boolean sorted, boolean filtred,Pageable pageable);
	
	Page <Ninja>  getAllPaged(Pageable pageable);

	Page <NinjaDTO> getNinjaFiltroOr(CreateComboNinjaDTO attributes, boolean sorted, boolean filtred,Pageable pageable);
	
	FormationNinjaDTO getFormationFinalResultAsync(String [] ninjas) throws InterruptedException,ExecutionException;
	
	FormationNinjaDTO getFormationFinalResult(String [] ninjas) throws InterruptedException;
	
	CompletableFuture<Ninja> getNinjaByName(String name) throws InterruptedException;
	
	FormationNinjaDTO createFormationWithNinjas(List<Ninja> ninjas,boolean awakenings);
	//List <SkillAttribute> createSkill(List<NinjaSkillKey> skillsKeys);

}
