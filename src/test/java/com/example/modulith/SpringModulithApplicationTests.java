package com.example.modulith;

import com.example.modulith.customer.service.CustomerService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class SpringModulithApplicationTests {

	@Inject
	CustomerService customerService;

	@Test
	void contextLoads() {

	}

}
