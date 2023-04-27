package com.alejandro.animeninja.bussines.model.dto;

public class RegisterDTO {
	
	private String mensaje;

	public RegisterDTO(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	public RegisterDTO() {
		super();
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	

}
