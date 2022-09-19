package com.alejandro.animeninja.bussines.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {

	private String code;
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorDTO(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public ErrorDTO() {
		super();
	}

}
