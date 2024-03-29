package com.alejandro.animeninja.bussines.auth.services;

import java.io.IOException;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.alejandro.animeninja.bussines.auth.SimpleGrantedAuthorityMixin;
import com.alejandro.animeninja.bussines.auth.filter.JWTAuthenticationFilter;
import com.alejandro.animeninja.bussines.exceptions.JwtValidationException;
import com.alejandro.animeninja.bussines.exceptions.UserException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTServiceImpl implements JWTService {

	 
	public static final long  EXPIRATION_DATE = 3600000*4;
	public static final String TOKEN_PREFIX = "Bearer ";
	
	
	@Override
	public String create(Authentication auth) throws IOException {
		
		String username = ((User) auth.getPrincipal()).getUsername();
		
		Collection <? extends GrantedAuthority>  roles = auth.getAuthorities();
		
		Claims claims = Jwts.claims();
		claims.put("authorities", new ObjectMapper().writeValueAsString(roles));
		
		
		String token = Jwts.builder().setClaims(claims).setSubject(username).signWith(SECRET_KEY)
				//.signWith(SignatureAlgorithm.HS512)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_DATE))
				.compact();
		
		return token;
	}

	@Override
	public boolean validate(String token) {
		
		try {
			getClaims(token);
			return true;
		}catch(JwtException |IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public Claims getClaims(String token) {
		try {
			Claims claims =  Jwts.parserBuilder()
					.setSigningKey(SECRET_KEY)
					.build()
					.parseClaimsJws(resolveToken(token))
					.getBody();
			return claims;
		}catch(JwtException | IllegalArgumentException e) {
	        throw new JwtValidationException("Token no válido", e);
	    }
	}

	@Override
	public String getUsername(String token) {
		if(token == null) {
			return null;
		}
		Claims claims = getClaims(token);
		if(claims != null) {
			return claims.getSubject();
		}
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getRoles(String token) throws IOException {
		Object roles = getClaims(token).get("authorities");
		
		Collection <? extends GrantedAuthority> authorities = Arrays.asList(new ObjectMapper()
				.addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
				.readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));		

		return authorities;
	}

	@Override
	public String resolveToken(String token) {
		if(token != null && token.startsWith(TOKEN_PREFIX)) {		
			return token.replace(TOKEN_PREFIX, "");
		}
		return null;
	}

}
