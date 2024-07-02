package com.example.hotel.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HotelApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelApiGatewayApplication.class, args);
	}

}
