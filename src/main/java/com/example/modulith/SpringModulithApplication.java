package com.example.modulith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.Modulithic;

@Modulithic(additionalPackages = "com.example.modulith", systemName = "Spring Modulith")
@SpringBootApplication
public class SpringModulithApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringModulithApplication.class, args);
	}

}
