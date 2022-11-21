package com.alejandro.animeninja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AnimeninjaApplication implements CommandLineRunner{

	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(AnimeninjaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		String pass = "erpepe";
		String pass2 = "12345";
		System.out.println(passwordEncoder.encode(pass));
		System.out.println(passwordEncoder.encode(pass2));
	}

}
