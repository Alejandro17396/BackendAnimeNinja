package com.alejandro.animeninja.bussines.validators.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.exceptions.UserException;
import com.alejandro.animeninja.bussines.model.Role;
import com.alejandro.animeninja.bussines.model.dto.RoleDTO;
import com.alejandro.animeninja.bussines.model.dto.UsuarioDTO;
import com.alejandro.animeninja.bussines.services.RoleService;
import com.alejandro.animeninja.bussines.validators.ValidatorUserService;

@Service
public class ValidatorUserServiceImpl implements ValidatorUserService {

	@Autowired
	private RoleService roleService;
	
	@Override
	public void validateUserDTO(UsuarioDTO usuario) {
		
		List <Role>  roles = roleService.getAll();
		
		if(usuario == null) {
			throw new UserException("400","Request body cant be null",HttpStatus.BAD_REQUEST);
		}
		
		if(usuario.getPassword() == null) {
			throw new UserException("400","password cant be null",HttpStatus.BAD_REQUEST);
		}
		
		if(usuario.getUsername() == null) {
			throw new UserException("400","username cant be null",HttpStatus.BAD_REQUEST);
		}
		
		if(usuario.getRoles() != null && !usuario.getRoles().isEmpty()) {
			for(RoleDTO role: usuario.getRoles()) {
				if(StringUtils.containsIgnoreCase(role.getAuthority(), "admin")) {
					throw new UserException("400","cant create account",HttpStatus.BAD_REQUEST);
				}
			}
			for(RoleDTO role: usuario.getRoles()) {
				if(!roles.contains(new Role(role.getAuthority()))) {
					throw new UserException("400","role doesn´t exists",HttpStatus.BAD_REQUEST);
				}
			}
			
		}
		
	}

}
