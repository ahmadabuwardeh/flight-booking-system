package com.bateekh.airways.flight_booking_system.dto;

import java.util.List;

public class FlightSearchResponse {
    private List<String> flightCodes;

    public FlightSearchResponse() {}

    public FlightSearchResponse(List<String> flightCodes) {
        this.flightCodes = flightCodes;
    }

    public List<String> getFlightCodes() {
        return flightCodes;
    }

    public void setFlightCodes(List<String> flightCodes) {
        this.flightCodes = flightCodes;
    }
}
