package com.bateekh.airways.flight_booking_system.service;

import com.bateekh.airways.flight_booking_system.exception.FlightNotFoundException;
import com.bateekh.airways.flight_booking_system.exception.InsufficientSeatsException;
import com.bateekh.airways.flight_booking_system.exception.InvalidBookingException;
import com.bateekh.airways.flight_booking_system.model.Booking;
import com.bateekh.airways.flight_booking_system.model.Flight;
import com.bateekh.airways.flight_booking_system.repository.BookingRepository;
import com.bateekh.airways.flight_booking_system.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    public Booking bookFlight(String flightCode, String passengerName, int passengers) {
        Flight flight = flightRepository.findById(flightCode)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found"));

        if (flight.getAvailableSeats() < passengers) {
            throw new InsufficientSeatsException("Not enough seats available.");
        }

        validateBookingCriteria(passengerName, passengers);

        flight.setAvailableSeats(flight.getAvailableSeats() - passengers);
        flightRepository.save(flight);

        Booking booking = new Booking();
        booking.setFlight(flight);
        booking.setPassengerName(passengerName);
        booking.setNumberOfPassengers(passengers);
        booking.setBookingDate(LocalDate.now());

        return bookingRepository.save(booking);
    }

    private void validateBookingCriteria(String passengerName, int passengers) {
        if (passengerName == null || passengerName.isEmpty()) {
            throw new InvalidBookingException("Passenger name cannot be null or empty.");
        }
        if (passengers < 1 || passengers > 7) {
            throw new InvalidBookingException("Passenger count must be between 1 and 7.");
        }
    }
}
