package com.alejandro.animeninja.bussines.auth.services;

import java.io.IOException;
import java.security.Key;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public interface JWTService {

	public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	public static final String HEADER_STRING = "Authorization";
	public String create(Authentication auth) throws IOException;
	public boolean validate(String token);
	public Claims getClaims(String token);
	public String getUsername(String token);
	public Collection <? extends GrantedAuthority> getRoles(String token) throws IOException;
	public String resolveToken(String token);
	
}
