package com.simuladorwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class SimuladorPrestamosApplication {//implements CommandLineRunner {

	/*
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	*/
	
	public static void main(String[] args) {
		SpringApplication.run(SimuladorPrestamosApplication.class, args);
	}
	
	/*@Override
	public void run(String... args) throws Exception {
		String password = "123456";
		for(int i=0; i<3; i++) {
			String bcryptPassword = passwordEncoder.encode(password);
			System.out.println(bcryptPassword);
		}
	}
	*/
}
