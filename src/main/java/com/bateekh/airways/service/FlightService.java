package com.bateekh.airways.service;

import com.bateekh.airways.model.Flight;
import com.bateekh.airways.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> searchFlights(String origin, String destination, String departureTime) {
        return flightRepository.findByOriginAndDestinationAndDepartureTime(origin, destination, departureTime);
    }
}
