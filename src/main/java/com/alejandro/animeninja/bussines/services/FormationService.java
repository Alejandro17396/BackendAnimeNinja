package com.alejandro.animeninja.bussines.services;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.transaction.annotation.Transactional;

import com.alejandro.animeninja.bussines.model.SkillType;
import com.alejandro.animeninja.bussines.model.UserFormation;
import com.alejandro.animeninja.bussines.model.dto.CompareFormationsDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateUserFormationCombosDTO;
import com.alejandro.animeninja.bussines.model.dto.CreateUserFormationDTO;
import com.alejandro.animeninja.bussines.model.dto.FormationNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.UserFormationDTO;

public interface FormationService {

	FormationNinjaDTO createFormation( HashMap<String,SkillType> request,boolean awakenings) throws InterruptedException, ExecutionException;
	UserFormation createUserFormation(CreateUserFormationDTO dto,String user) throws InterruptedException, ExecutionException;
	UserFormation updateUserFormation(CreateUserFormationDTO dto,String user) throws InterruptedException, ExecutionException;
	UserFormation createOrUpdateUserFormation(CreateUserFormationDTO dto,String user) throws InterruptedException, ExecutionException;
	UserFormation getUserFormationById(Long id);
	UserFormation getUserFormationByName(String name,String user);
	boolean deleteUserFormationByName(String name,String user);
	List <UserFormationDTO> getFormationsByUser(String user);
	CompareFormationsDTO compareFormations(CompareFormationsDTO formations);
	UserFormationDTO setNinjasPosition(UserFormationDTO formation);
	
	@Transactional
	UserFormation saveUserFormation(UserFormation entity);
	List <UserFormationDTO> createUserComboFormation(CreateUserFormationCombosDTO dto,String username);
	UserFormationDTO mergeBonus(UserFormation entity) throws InterruptedException, ExecutionException ;
	
	
}
