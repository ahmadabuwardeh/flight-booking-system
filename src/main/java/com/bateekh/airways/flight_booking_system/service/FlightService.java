package com.bateekh.airways.flight_booking_system.service;

import com.bateekh.airways.flight_booking_system.dto.FlightSearchResponse;
import com.bateekh.airways.flight_booking_system.exception.InvalidSearchCriteriaException;
import com.bateekh.airways.flight_booking_system.model.Flight;
import com.bateekh.airways.flight_booking_system.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<FlightSearchResponse> searchFlights(String origin, String destination, LocalDate departureDate, LocalDate returnDate, int passengers) {
        validateSearchCriteria(origin, destination, departureDate, returnDate, passengers);

        // Fetch the outbound flights
        List<Flight> outboundFlights = flightRepository.findByOriginAndDestinationAndDepartureDate(origin, destination, departureDate);

        // For round-trip
        if (returnDate != null) {
            // Fetch the return flights
            List<Flight> returnFlights = flightRepository.findByOriginAndDestinationAndDepartureDate(destination, origin, returnDate);

            // Generate all combinations of outbound and return flights
            List<FlightSearchResponse> roundTripResponses = new ArrayList<>();
            for (Flight outbound : outboundFlights) {
                for (Flight returnFlight : returnFlights) {
                    if (outbound.getAvailableSeats() >= passengers && returnFlight.getAvailableSeats() >= passengers) {
                        roundTripResponses.add(new FlightSearchResponse(List.of(outbound.getFlightCode(), returnFlight.getFlightCode())));
                    }
                }
            }
            return roundTripResponses;
        }

        // For one-way trip
        return outboundFlights.stream()
                .filter(flight -> flight.getAvailableSeats() >= passengers)
                .map(flight -> new FlightSearchResponse(List.of(flight.getFlightCode())))
                .collect(Collectors.toList());
    }

    private void validateSearchCriteria(String origin, String destination, LocalDate departureDate, LocalDate returnDate, int passengers) {
        if (origin == null || origin.isEmpty() || destination == null || destination.isEmpty()) {
            throw new InvalidSearchCriteriaException("Origin and destination cannot be null or empty.");
        }
        if (departureDate.isAfter(LocalDate.now().plusDays(30))) {
            throw new InvalidSearchCriteriaException("Cannot search for flights beyond 30 days from today.");
        }
        if (returnDate != null && returnDate.isBefore(departureDate)) {
            throw new InvalidSearchCriteriaException("Return date cannot be before the departure date.");
        }
        if (passengers < 1 || passengers > 7) {
            throw new InvalidSearchCriteriaException("Passenger count must be between 1 and 7.");
        }
    }
}
