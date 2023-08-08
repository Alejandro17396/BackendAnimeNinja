package com.alejandro.animeninja.bussines.auth.filter;

import java.io.IOException;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.alejandro.animeninja.bussines.auth.SimpleGrantedAuthorityMixin;
import com.alejandro.animeninja.bussines.auth.services.JWTService;
import com.alejandro.animeninja.bussines.auth.services.JWTServiceImpl;
import com.alejandro.animeninja.bussines.exceptions.JwtValidationException;
import com.alejandro.animeninja.bussines.exceptions.UserException;
import com.alejandro.animeninja.bussines.model.dto.ErrorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter{
	
	private JWTService jwtService;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager,JWTService jwtService) {
		super(authenticationManager);
		this.jwtService= jwtService;
	}

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String header = request.getHeader(JWTServiceImpl.HEADER_STRING);
		
		if(!requiresAuthentication(header)) {
			chain.doFilter(request, response);
			return;
		}
		
		
		
		UsernamePasswordAuthenticationToken authentication = null;
		
		try {
		    if (!jwtService.validate(header)) {
		        throw new JwtValidationException("Token no válido");
		    }

		   /* jwtService.getUsername(header);
		    jwtService.getRoles(header);*/

		    authentication = new UsernamePasswordAuthenticationToken(
		        jwtService.getUsername(header), 
		        null, 
		        jwtService.getRoles(header)
		    );
		} catch(JwtValidationException e) {
		    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		    response.setContentType("application/json");

		    Map<String, Object> errorResponse = new HashMap<>();
		    errorResponse.put("mensaje", e.getMessage());
		    
		    ErrorDTO error = new ErrorDTO();
			error.setCode("401");
			error.setMessage(e.getMessage());

		    response.getWriter().write(new ObjectMapper().writeValueAsString(error));

		    return;
		}
		
		/*if(jwtService.validate(header)) {
				
			jwtService.getUsername(header);
			try {
				jwtService.getRoles(header);
				authentication = new UsernamePasswordAuthenticationToken(jwtService.getUsername(header), null, jwtService.getRoles(header));
			}catch(JwtValidationException e) {
			    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			    response.setContentType("application/json");

			    Map<String, Object> errorResponse = new HashMap<>();
			    errorResponse.put("mensaje", e.getMessage());

			    response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));

			    return;
			}
			
		}*/

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
		
	}
	
	
	protected boolean requiresAuthentication(String header) {
		
		if(header == null || !header.startsWith(JWTServiceImpl.TOKEN_PREFIX)) {
			return false;
		}
		return true;
	}
	
	
	

}
