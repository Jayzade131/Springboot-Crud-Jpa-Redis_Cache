package com.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CrudTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudTaskApplication.class, args);
	}

}
