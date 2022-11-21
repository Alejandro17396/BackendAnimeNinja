package com.alejandro.animeninja.bussines.auth.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.alejandro.animeninja.bussines.auth.services.JWTService;
import com.alejandro.animeninja.bussines.auth.services.JWTServiceImpl;
import com.alejandro.animeninja.bussines.model.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private JWTService jwtService;
	private Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager,JWTService jwtService) {
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		
		
		if(username != null && password != null) {
			logger.info("Username usando form data : " + username);
			logger.info("Password usando form data : " + password);
		}else {
			Usuario usuario = null;
			try {
				usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
				username = usuario.getUsername();
				password = usuario.getPassword();
				
				logger.info("Username usando json : " + username);
				logger.info("Password usando json : " + password);
			}catch (Exception e) {
				
			}
		}

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

		return authenticationManager.authenticate(authToken);
	}

	public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		/*String key =new String(SECRET_KEY.getEncoded(), StandardCharsets.US_ASCII);
		System.out.println(key);*/
		
		/*String username = ((User) authResult.getPrincipal()).getUsername();
		
		Collection <? extends GrantedAuthority>  roles = authResult.getAuthorities();
		
		Claims claims = Jwts.claims();
		claims.put("authorities", new ObjectMapper().writeValueAsString(roles));
		
		
		String token = Jwts.builder().setClaims(claims).setSubject(username).signWith(SECRET_KEY)
				//.signWith(SignatureAlgorithm.HS512)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+3600000*4))
				.compact();*/
		String token = jwtService.create(authResult);

		response.addHeader(JWTServiceImpl.HEADER_STRING, JWTServiceImpl.TOKEN_PREFIX + token);

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("token", token);
		body.put("user", (User) authResult.getPrincipal());
		body.put("mensaje", String.format("Hola %s, has iniciado sesion con exito",authResult.getName()));
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(200);
		response.setContentType("application/json");
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
	
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("mensaje", "Authentication error: username o password invalid!");
		body.put("error", failed.getMessage());
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(401);
		response.setContentType("application/json");
		
	}
	
}
