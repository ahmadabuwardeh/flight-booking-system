package com.bateekh.airways.flight_booking_system.service;

import com.bateekh.airways.flight_booking_system.dto.FlightSearchResponse;
import com.bateekh.airways.flight_booking_system.exception.InvalidSearchCriteriaException;
import com.bateekh.airways.flight_booking_system.model.Flight;
import com.bateekh.airways.flight_booking_system.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightService flightService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchFlights_OneWay_Success() {
        LocalDate departureDate = LocalDate.of(2024, 9, 22);
        String origin = "AMM";
        String destination = "DXB";
        int passengers = 2;

        Flight flight1 = new Flight();
        flight1.setFlightCode("BA-101");
        flight1.setAvailableSeats(50);

        Flight flight2 = new Flight();
        flight2.setFlightCode("BA-102");
        flight2.setAvailableSeats(50);

        when(flightRepository.findByOriginAndDestinationAndDepartureDate(origin, destination, departureDate))
                .thenReturn(Arrays.asList(flight1, flight2));

        List<FlightSearchResponse> responses = flightService.searchFlights(origin, destination, departureDate, null, passengers);

        assertNotNull(responses);
        assertEquals(2, responses.size());
        assertEquals(1, responses.get(0).getFlightCodes().size());
    }

    @Test
    void testSearchFlights_RoundTrip_Success() {
        LocalDate departureDate = LocalDate.of(2024, 9, 22);
        LocalDate returnDate = LocalDate.of(2024, 9, 26);
        String origin = "AMM";
        String destination = "DXB";
        int passengers = 2;

        Flight outbound1 = new Flight();
        outbound1.setFlightCode("BA-101");
        outbound1.setAvailableSeats(50);

        Flight outbound2 = new Flight();
        outbound2.setFlightCode("BA-102");
        outbound2.setAvailableSeats(50);

        Flight return1 = new Flight();
        return1.setFlightCode("BA-201");
        return1.setAvailableSeats(50);

        Flight return2 = new Flight();
        return2.setFlightCode("BA-202");
        return2.setAvailableSeats(50);

        when(flightRepository.findByOriginAndDestinationAndDepartureDate(origin, destination, departureDate))
                .thenReturn(Arrays.asList(outbound1, outbound2));

        when(flightRepository.findByOriginAndDestinationAndDepartureDate(destination, origin, returnDate))
                .thenReturn(Arrays.asList(return1, return2));

        List<FlightSearchResponse> responses = flightService.searchFlights(origin, destination, departureDate, returnDate, passengers);

        assertNotNull(responses);
        assertEquals(4, responses.size());
    }

    @Test
    void testSearchFlights_NoFlightsAvailable() {
        LocalDate departureDate = LocalDate.of(2024, 9, 22);
        String origin = "AMM";
        String destination = "DXB";
        int passengers = 2;

        when(flightRepository.findByOriginAndDestinationAndDepartureDate(origin, destination, departureDate))
                .thenReturn(Collections.emptyList());

        List<FlightSearchResponse> responses = flightService.searchFlights(origin, destination, departureDate, null, passengers);

        assertTrue(responses.isEmpty());
    }

    @Test
    void testSearchFlights_InvalidSearchCriteria() {
        LocalDate departureDate = LocalDate.of(2024, 9, 22);
        int passengers = 2;

        assertThrows(InvalidSearchCriteriaException.class, () ->
                flightService.searchFlights("", "DXB", departureDate, null, passengers));

        assertThrows(InvalidSearchCriteriaException.class, () ->
                flightService.searchFlights("AMM", "", departureDate, null, passengers));

        assertThrows(InvalidSearchCriteriaException.class, () ->
                flightService.searchFlights("AMM", "DXB", departureDate.plusDays(31), null, passengers));

        assertThrows(InvalidSearchCriteriaException.class, () ->
                flightService.searchFlights("AMM", "DXB", departureDate, null, 0));

        assertThrows(InvalidSearchCriteriaException.class, () ->
                flightService.searchFlights("AMM", "DXB", departureDate, null, 8));
    }
}
