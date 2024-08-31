package com.bateekh.airways.flight_booking_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.bateekh.airways.flight_booking_system")
@EntityScan(basePackages = "com.bateekh.airways.flight_booking_system.model")
@EnableJpaRepositories(basePackages = "com.bateekh.airways.flight_booking_system.repository")
public class FlightBookingSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(FlightBookingSystemApplication.class, args);
	}
}

