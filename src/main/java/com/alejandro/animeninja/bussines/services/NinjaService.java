package com.alejandro.animeninja.bussines.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.alejandro.animeninja.bussines.model.Ninja;
import com.alejandro.animeninja.bussines.model.NinjaUserFormation;
import com.alejandro.animeninja.bussines.model.dto.CreateComboNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateNinjaEquipmentDTO;
import com.alejandro.animeninja.bussines.model.dto.FormationNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.NinjaUserFormationDTO;

public interface NinjaService {

	List <Ninja> getAll();
	
	List <Ninja> getNinjasBySpecification(Specification <Ninja> specification,Pageable pageable);
	
	Ninja getNinja(String clave);
	
	List<FormationNinjaDTO> getNinjaComboFormations(CreateComboNinjaDTO attributes,boolean merge,boolean sorted,boolean filtred,
									boolean or,boolean awakenings);
	
	Page <NinjaDTO> getNinjaFiltroAnd(CreateComboNinjaDTO attributes, boolean sorted, boolean filtred,Pageable pageable);
	
	Page <Ninja>  getAllPaged(Pageable pageable);

	Page <NinjaDTO> getNinjaFiltroOr(CreateComboNinjaDTO attributes, boolean sorted, boolean filtred,Pageable pageable);
	
	FormationNinjaDTO getFormationFinalResultAsync(String [] ninjas) throws InterruptedException,ExecutionException;
	
	FormationNinjaDTO getFormationFinalResult(String [] ninjas) throws InterruptedException;
	
	CompletableFuture<Ninja> getNinjaByName(String name) throws InterruptedException;
	
	FormationNinjaDTO createFormationWithNinjas(List<Ninja> ninjas,boolean awakenings);

	NinjaUserFormation saveUserSet(NinjaUserFormation accesories);

	NinjaUserFormationDTO mergeBonus(NinjaUserFormation accesories);

	NinjaUserFormation createNinjaFormationById(CreateNinjaEquipmentDTO dto, String user);
	
	NinjaUserFormation createOrUpdateNinjaFormationByNameAndUsername(CreateNinjaEquipmentDTO dto, String user);
	
	NinjaUserFormation createNinjaFormationByNameAndUsername(CreateNinjaEquipmentDTO dto, String user);
	
	NinjaUserFormation createMockUserNinja(CreateNinjaEquipmentDTO ninja);
	
	NinjaUserFormation updateNinjaFormationByNameAndUsername(CreateNinjaEquipmentDTO dto, String user);
	
	NinjaUserFormation getNinjaByName(String name,String user);
	
	boolean deleteNinjaByName(String name,String user);
	
	List <NinjaUserFormationDTO> getNinjasByUser(String user);
	
	

}
