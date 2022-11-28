package com.alejandro.animeninja.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.animeninja.bussines.model.dto.UsuarioDTO;
import com.alejandro.animeninja.bussines.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/create")
	public ResponseEntity <String> createUser(@RequestBody(required = false) UsuarioDTO user){
		
		ResponseEntity <String> response = null;
		if(userService.create(user)) {
			response = new ResponseEntity <>("Cuenta creada con éxito",HttpStatus.OK);
		}else {
			response = new ResponseEntity <>("Error al crear la cuenta",HttpStatus.BAD_REQUEST);
		}
		
		return response;
	}

}
