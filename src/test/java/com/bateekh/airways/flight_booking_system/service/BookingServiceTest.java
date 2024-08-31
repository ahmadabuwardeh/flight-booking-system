package com.bateekh.airways.flight_booking_system.service;

import com.bateekh.airways.flight_booking_system.exception.FlightNotFoundException;
import com.bateekh.airways.flight_booking_system.exception.InsufficientSeatsException;
import com.bateekh.airways.flight_booking_system.exception.InvalidBookingException;
import com.bateekh.airways.flight_booking_system.model.Booking;
import com.bateekh.airways.flight_booking_system.model.Flight;
import com.bateekh.airways.flight_booking_system.repository.BookingRepository;
import com.bateekh.airways.flight_booking_system.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBookFlight_Success() {
        String flightCode = "BA-101";
        String passengerName = "John Doe";
        int passengers = 2;

        Flight flight = new Flight();
        flight.setFlightCode(flightCode);
        flight.setAvailableSeats(50);

        when(flightRepository.findById(flightCode)).thenReturn(Optional.of(flight));

        Booking booking = new Booking();
        booking.setFlight(flight);
        booking.setPassengerName(passengerName);
        booking.setNumberOfPassengers(passengers);

        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        Booking result = bookingService.bookFlight(flightCode, passengerName, passengers);

        assertNotNull(result);
        assertEquals(passengerName, result.getPassengerName());
        assertEquals(flightCode, result.getFlight().getFlightCode());
        assertEquals(passengers, result.getNumberOfPassengers());

        verify(flightRepository).save(flight);
        assertEquals(48, flight.getAvailableSeats());
    }

    @Test
    void testBookFlight_FlightNotFound() {
        String flightCode = "BA-999";
        String passengerName = "John Doe";
        int passengers = 2;

        when(flightRepository.findById(flightCode)).thenReturn(Optional.empty());

        assertThrows(FlightNotFoundException.class, () ->
                bookingService.bookFlight(flightCode, passengerName, passengers));
    }

    @Test
    void testBookFlight_InsufficientSeats() {
        String flightCode = "BA-101";
        String passengerName = "John Doe";
        int passengers = 51;

        Flight flight = new Flight();
        flight.setFlightCode(flightCode);
        flight.setAvailableSeats(50);

        when(flightRepository.findById(flightCode)).thenReturn(Optional.of(flight));

        assertThrows(InsufficientSeatsException.class, () ->
                bookingService.bookFlight(flightCode, passengerName, passengers));
    }

    @Test
    void testBookFlight_InvalidBookingCriteria() {
        String flightCode = "BA-101";
        String passengerName = "";
        int passengers = 0;

        Flight flight = new Flight();
        flight.setFlightCode(flightCode);
        flight.setAvailableSeats(50);

        when(flightRepository.findById(flightCode)).thenReturn(Optional.of(flight));

        assertThrows(InvalidBookingException.class, () ->
                bookingService.bookFlight(flightCode, passengerName, passengers)
        );
    }
}
