package com.bateekh.airways.flight_booking_system.controller;

import com.bateekh.airways.flight_booking_system.dto.FlightSearchResponse;
import com.bateekh.airways.flight_booking_system.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/search")
    public List<FlightSearchResponse> searchFlights(@RequestParam String origin,
                                                    @RequestParam String destination,
                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
                                                    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate,
                                                    @RequestParam int passengers) {
        return flightService.searchFlights(origin, destination, departureDate, returnDate, passengers);
    }
}
