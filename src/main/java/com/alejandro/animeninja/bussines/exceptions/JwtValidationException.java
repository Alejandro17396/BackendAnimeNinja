package com.alejandro.animeninja.bussines.exceptions;

import org.springframework.security.core.AuthenticationException;

public class JwtValidationException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public JwtValidationException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtValidationException(String msg) {
        super(msg);
    }
}