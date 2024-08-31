package com.bateekh.airways.flight_booking_system.controller;

import com.bateekh.airways.flight_booking_system.model.Booking;
import com.bateekh.airways.flight_booking_system.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public Booking bookFlight(@RequestParam String flightCode,
                              @RequestParam String passengerName,
                              @RequestParam int passengers) {
        return bookingService.bookFlight(flightCode, passengerName, passengers);
    }
}
