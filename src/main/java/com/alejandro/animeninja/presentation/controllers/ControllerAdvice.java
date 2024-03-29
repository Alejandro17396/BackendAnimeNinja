package com.alejandro.animeninja.presentation.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.alejandro.animeninja.bussines.exceptions.AccesoriesException;
import com.alejandro.animeninja.bussines.exceptions.CreateAccesoriesException;
import com.alejandro.animeninja.bussines.exceptions.CreateNinjaException;
import com.alejandro.animeninja.bussines.exceptions.CreateSetException;
import com.alejandro.animeninja.bussines.exceptions.FileException;
import com.alejandro.animeninja.bussines.exceptions.FormationException;
import com.alejandro.animeninja.bussines.exceptions.JsonMapperException;
import com.alejandro.animeninja.bussines.exceptions.NinjaFilterException;
import com.alejandro.animeninja.bussines.exceptions.NinjaSkillException;
import com.alejandro.animeninja.bussines.exceptions.NinjaUserException;
import com.alejandro.animeninja.bussines.exceptions.SetException;
import com.alejandro.animeninja.bussines.exceptions.UserException;
import com.alejandro.animeninja.bussines.exceptions.UserFormationException;
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
	
	@ExceptionHandler(value = AccesoriesException.class)
	public ResponseEntity<ErrorDTO> setExceptionHandler(AccesoriesException exception){
		ErrorDTO error = new ErrorDTO();
		error.setCode(exception.getCode());
		error.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorDTO>(error,exception.getStatus());
	}
	
	@ExceptionHandler(value = UserException.class)
	public ResponseEntity<ErrorDTO> setExceptionHandler(UserException exception){
		ErrorDTO error = new ErrorDTO();
		error.setCode(exception.getCode());
		error.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorDTO>(error,exception.getStatus());
	}
	
	@ExceptionHandler(value = UserFormationException.class)
	public ResponseEntity<ErrorDTO> setExceptionHandler(UserFormationException exception){
		ErrorDTO error = new ErrorDTO();
		error.setCode(exception.getCode());
		error.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorDTO>(error,exception.getStatus());
	}
	
	@ExceptionHandler(value = CreateNinjaException.class)
	public ResponseEntity<ErrorDTO> setExceptionHandler(CreateNinjaException exception){
		ErrorDTO error = new ErrorDTO();
		error.setCode(exception.getCode());
		error.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorDTO>(error,exception.getStatus());
	}
	
	@ExceptionHandler(value = CreateSetException.class)
	public ResponseEntity<ErrorDTO> setExceptionHandler(CreateSetException exception){
		ErrorDTO error = new ErrorDTO();
		error.setCode(exception.getCode());
		error.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorDTO>(error,exception.getStatus());
	}
	
	@ExceptionHandler(value = CreateAccesoriesException.class)
	public ResponseEntity<ErrorDTO> setExceptionHandler(CreateAccesoriesException exception){
		ErrorDTO error = new ErrorDTO();
		error.setCode(exception.getCode());
		error.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorDTO>(error,exception.getStatus());
	}
	
	@ExceptionHandler(value = FormationException.class)
	public ResponseEntity<ErrorDTO> setExceptionHandler(FormationException exception){
		ErrorDTO error = new ErrorDTO();
		error.setCode(exception.getCode());
		error.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorDTO>(error,exception.getStatus());
	}
	
	@ExceptionHandler(value = NinjaUserException.class)
	public ResponseEntity<ErrorDTO> setExceptionHandler(NinjaUserException exception){
		ErrorDTO error = new ErrorDTO();
		error.setCode(exception.getCode());
		error.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorDTO>(error,exception.getStatus());
	}
	
	@ExceptionHandler(value = JsonMapperException.class)
	public ResponseEntity<ErrorDTO> setExceptionHandler(JsonMapperException exception){
		ErrorDTO error = new ErrorDTO();
		error.setCode(exception.getCode());
		error.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorDTO>(error,exception.getStatus());
	}
	
	@ExceptionHandler(value = FileException.class)
	public ResponseEntity<ErrorDTO> setExceptionHandler(FileException exception){
		ErrorDTO error = new ErrorDTO();
		error.setCode(exception.getCode());
		error.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorDTO>(error,exception.getStatus());
	}
	
}
