package com.example.parking_web_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan
public class ParkingWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingWebApplication.class, args);
	}

}
