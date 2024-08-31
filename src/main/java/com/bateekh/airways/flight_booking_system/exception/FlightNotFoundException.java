package com.bateekh.airways.flight_booking_system.exception;

public class FlightNotFoundException extends RuntimeException {

    public FlightNotFoundException(String message) {
        super(message);
    }
}
