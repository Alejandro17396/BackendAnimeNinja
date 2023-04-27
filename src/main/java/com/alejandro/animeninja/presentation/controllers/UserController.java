package com.alejandro.animeninja.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.model.dto.RegisterDTO;
import com.alejandro.animeninja.bussines.model.dto.UsuarioDTO;
import com.alejandro.animeninja.bussines.services.UserService;
import com.alejandro.animeninja.bussines.validators.ValidatorUserService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ValidatorUserService userValidator;
	
	@PostMapping("/create")
	public ResponseEntity <RegisterDTO> createUser(@RequestBody(required = false) UsuarioDTO user){
		
		userValidator.validateUserDTO(user);
		RegisterDTO result = new RegisterDTO();
		result.setMensaje("Cuenta creada con éxito");
		ResponseEntity <RegisterDTO> response = null;
		if(userService.create(user)) {
			response = new ResponseEntity <>(result,HttpStatus.OK);
		}else {
			response = new ResponseEntity <>(new RegisterDTO("Error al crear la cuenta"),HttpStatus.BAD_REQUEST);
		}
		
		return response;
	}

}
