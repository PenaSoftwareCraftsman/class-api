package com.unitech.classapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.*;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ClassApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassApiApplication.class, args);
	}

}
