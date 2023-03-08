package com.alejandro.animeninja.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.alejandro.animeninja.bussines.auth.filter.JWTAuthenticationFilter;
import com.alejandro.animeninja.bussines.auth.filter.JWTAuthorizationFilter;
import com.alejandro.animeninja.bussines.auth.services.JWTService;
import com.alejandro.animeninja.bussines.services.impl.JpaUserDetailsService;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
		
		//UserBuilder users = User.withDefaultPasswordEncoder();
		/*PasswordEncoder encoder = passwordEncoder();
		UserBuilder users = User.builder().passwordEncoder(encoder::encode);
		
		builder.inMemoryAuthentication()
		.withUser(users.username("Todomelocomo").password("fairy34505")
				.roles("ADMIN","USER"))
		.withUser(users.username("Jose").password("fairy34505")
				.roles("USER"));*/
		
		builder.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
		//.usersByUsernameQuery("select username,password,enabled from users where username=?")
		//.authoritiesByUsernameQuery("select u.username ,a.authority from authorities a inner join users u on (a.user_id=u.id) where u.username=?");   
		
	}
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(HttpSecurity http ) throws Exception{
		
		http.authorizeRequests().
		antMatchers("/accesorios/**","/equipos/**","/sets/**","/skills/**","/partes/**","/bonuses/**","/atributos/**","/accesories/**","/users/**","/formation/**","/").permitAll()
		.antMatchers("/ninjas/**").hasAnyRole("USER")
		.anyRequest().authenticated()
		.and()
		.addFilter(new JWTAuthenticationFilter(authenticationManagerBean(),jwtService))
		.addFilter(new JWTAuthorizationFilter(authenticationManagerBean(),jwtService))
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
	
	
	@Autowired
	private DataSource dataSource;
}
