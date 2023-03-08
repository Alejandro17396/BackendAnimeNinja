package com.alejandro.animeninja.bussines.services;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.springframework.transaction.annotation.Transactional;

import com.alejandro.animeninja.bussines.model.SkillType;
import com.alejandro.animeninja.bussines.model.UserFormation;
import com.alejandro.animeninja.bussines.model.dto.CreateUserFormationDTO;
import com.alejandro.animeninja.bussines.model.dto.FormationNinjaDTO;
import com.alejandro.animeninja.bussines.model.dto.UserFormationDTO;

public interface FormationService {

	FormationNinjaDTO createFormation( HashMap<String,SkillType> request,boolean awakenings) throws InterruptedException, ExecutionException;
	UserFormation createUserFormation(CreateUserFormationDTO dto) throws InterruptedException, ExecutionException;
	UserFormation getUserFormationById(Long id);
	
	@Transactional
	UserFormation saveUserFormation(UserFormation entity);
	
	UserFormationDTO mergeBonus(UserFormation entity) throws InterruptedException, ExecutionException ;
}
