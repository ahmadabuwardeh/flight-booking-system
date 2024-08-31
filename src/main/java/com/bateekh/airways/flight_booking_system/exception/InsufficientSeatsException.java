package com.bateekh.airways.flight_booking_system.exception;

public class InsufficientSeatsException extends RuntimeException {
  public InsufficientSeatsException(String message) {
    super(message);
  }
}
