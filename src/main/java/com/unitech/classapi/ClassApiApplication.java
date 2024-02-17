package com.unitech.classapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication()
@EnableCaching
public class ClassApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClassApiApplication.class, args);
	}

}
