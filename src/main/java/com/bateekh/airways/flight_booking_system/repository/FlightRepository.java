package com.bateekh.airways.flight_booking_system.repository;

import com.bateekh.airways.flight_booking_system.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, String> {
    List<Flight> findByOriginAndDestinationAndDepartureDate(String origin, String destination, LocalDate departureDate);
}
