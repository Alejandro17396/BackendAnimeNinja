package com.alejandro.animeninja.presentation.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.alejandro.animeninja.bussines.exceptions.NinjaFilterException;
import com.alejandro.animeninja.bussines.exceptions.NinjaSkillException;
import com.alejandro.animeninja.bussines.exceptions.SetException;
import com.alejandro.animeninja.bussines.model.dto.ErrorDTO;

@RestControllerAdvice
public class ControllerAdvice {

	/*@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<ErrorDTO> runtimeExceptionHandler(RuntimeException exception){
		ErrorDTO error = new ErrorDTO();
		return new ResponseEntity<ErrorDTO>(error,HttpStatus.I_AM_A_TEAPOT);
	}*/
	
	@ExceptionHandler(value = NinjaFilterException.class)
	public ResponseEntity<ErrorDTO> NinjaFilterExceptionHandler(NinjaFilterException exception){
		ErrorDTO error = new ErrorDTO();
		error.setCode(exception.getCode());
		error.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorDTO>(error,exception.getStatus());
	}
	
	@ExceptionHandler(value = NinjaSkillException.class)
	public ResponseEntity<ErrorDTO> ninjaSkillExceptionHandler(NinjaSkillException exception){
		ErrorDTO error = new ErrorDTO();
		error.setCode(exception.getCode());
		error.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorDTO>(error,exception.getStatus());
	}
	
	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorDTO> typeMismatchExceptionHandler(MethodArgumentTypeMismatchException exception){
		ErrorDTO error = new ErrorDTO();
		error.setCode("400");
		error.setMessage("Failed to convert value to required type");
		return new ResponseEntity<ErrorDTO>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = SetException.class)
	public ResponseEntity<ErrorDTO> setExceptionHandler(SetException exception){
		ErrorDTO error = new ErrorDTO();
		error.setCode(exception.getCode());
		error.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorDTO>(error,exception.getStatus());
	}
	
}
