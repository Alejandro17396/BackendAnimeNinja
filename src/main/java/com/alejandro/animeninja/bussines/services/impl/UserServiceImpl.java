package com.alejandro.animeninja.bussines.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.animeninja.bussines.exceptions.AccesoriesException;
import com.alejandro.animeninja.bussines.exceptions.UserException;
import com.alejandro.animeninja.bussines.mappers.UsuarioMapper;
import com.alejandro.animeninja.bussines.model.Role;
import com.alejandro.animeninja.bussines.model.Usuario;
import com.alejandro.animeninja.bussines.model.dto.UsuarioDTO;
import com.alejandro.animeninja.bussines.services.UserService;
import com.alejandro.animeninja.integration.repositories.UsuarioRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public boolean create(UsuarioDTO usuario) {
		Usuario user = usuarioMapper.toEntity(usuario);
		Usuario result = null;
		checkAuthoritiy(user);
		userNameFree(user.getUsername());
		mailFree(user.getMail());
		user.setPassword(encryptPassword(user.getPassword()));
		result = usuarioRepository.save(user);
		
		return result != null ? true : false;
	}
	
	private void mailFree(String mail) {
		Usuario usuario = usuarioRepository.findByMail(mail);
		if(usuario != null) {
			throw new UserException("400","cant create account mail alredy exists",HttpStatus.BAD_REQUEST);
		}
	}

	private String encryptPassword(String password) {
		return passwordEncoder.encode(password);
	}

	private void userNameFree(String username) {
		Usuario usuario = usuarioRepository.findByUsername(username);
		if(usuario != null) {
			throw new UserException("400","cant create account username alredy exists",HttpStatus.BAD_REQUEST);
		}
	}

	private void checkAuthoritiy(Usuario user) {
		List <Role>  roles = user.getRoles();
		if(roles == null || roles.isEmpty()) {
		 roles = new ArrayList <>();
		 user.getRoles().add(new Role("ROLE_USER"));
		}
	}

}
