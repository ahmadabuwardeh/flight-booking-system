package com.bateekh.airways.repository;

import com.bateekh.airways.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, String> {
    List<Flight> findByOriginAndDestinationAndDepartureTime(String origin, String destination, String departureTime);
}
