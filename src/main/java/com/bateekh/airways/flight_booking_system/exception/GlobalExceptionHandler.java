package com.bateekh.airways.flight_booking_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(FlightNotFoundException.class)
  public ResponseEntity<String> handleFlightNotFoundException(FlightNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InsufficientSeatsException.class)
  public ResponseEntity<String> handleInsufficientSeatsException(InsufficientSeatsException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidBookingException.class)
  public ResponseEntity<String> handleInvalidBookingException(InvalidBookingException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidSearchCriteriaException.class)
  public ResponseEntity<String> handleInvalidSearchCriteriaException(InvalidSearchCriteriaException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGeneralException(Exception ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
