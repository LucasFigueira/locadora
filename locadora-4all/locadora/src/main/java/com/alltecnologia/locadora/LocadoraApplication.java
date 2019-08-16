package com.alltecnologia.locadora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LocadoraApplication {

	public static void main(String[] args) { 
		SpringApplication.run(LocadoraApplication.class, args);
		System.out.print(new BCryptPasswordEncoder().encode("123"));
	}

}
