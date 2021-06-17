package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })     //remove the default behavior of login

public class MaSbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaSbackendApplication.class, args);
	}

}
