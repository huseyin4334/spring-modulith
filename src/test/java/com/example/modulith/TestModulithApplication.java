package com.example.modulith;

import org.springframework.boot.SpringApplication;

public class TestModulithApplication {

	public static void main(String[] args) {
		SpringApplication.from(TestModulithApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
