package com.alejandro.animeninja.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.alejandro.animeninja.bussines.auth.filter.JWTAuthenticationFilter;
import com.alejandro.animeninja.bussines.auth.filter.JWTAuthorizationFilter;
import com.alejandro.animeninja.bussines.auth.services.JWTService;
import com.alejandro.animeninja.bussines.services.impl.JpaUserDetailsService;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JpaUserDetailsService userDetailsService;

	@Autowired
	private JWTService jwtService;

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {

		builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/ninjas/*", "/accesorios/**", "/equipos/**", "/sets/**", "/skills/**", "/partes/**",
						"/bonuses/**", "/atributos/**", "/accesories/**", "/users/**", "/formation/**", "/")
				.permitAll()
				// .antMatchers("/ninjas/**").hasAnyRole("USER")
				.anyRequest().authenticated().and()
				.addFilter(new JWTAuthenticationFilter(authenticationManagerBean(), jwtService))
				.addFilter(new JWTAuthorizationFilter(authenticationManagerBean(), jwtService)).csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}
}
