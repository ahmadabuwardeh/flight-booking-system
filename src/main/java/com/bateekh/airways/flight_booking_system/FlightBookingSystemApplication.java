package com.bateekh.airways.flight_booking_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.bateekh.airways.repository")
public class FlightBookingSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(FlightBookingSystemApplication.class, args);
	}
}

