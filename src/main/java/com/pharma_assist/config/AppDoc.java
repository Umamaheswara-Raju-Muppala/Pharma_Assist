package com.pharma_assist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class AppDoc {

	Info info() {
		return new Info().title("Pharmacy Assistant")
				.description("The Pharmacy Assistant System is a RESTful web service built using Spring Boot. "
						+ "This API provides comprehensive methods for managing pharmacy operations, including "
						+ "medication inventory, prescription processing, and patient management. "
						+ "It covers all CRUD operations on pharmacy entities.");
	}

	@Bean
	OpenAPI openAPI() {
		return new OpenAPI().info(info());
	}

}
