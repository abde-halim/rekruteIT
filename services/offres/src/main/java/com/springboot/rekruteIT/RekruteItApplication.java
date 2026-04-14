package com.springboot.rekruteIT;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * Main application class for Offres Microservice
 * 
 * @EnableFeignClients: Enables Feign clients for inter-service communication
 *                      This allows the service to make REST calls to other
 *                      services (e.g., Recruteur Service)
 */
@SpringBootApplication
@EnableFeignClients
public class RekruteItApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(RekruteItApplication.class, args);
	}
}
