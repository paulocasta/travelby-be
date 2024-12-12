package com.travelby.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlightDTO {

    private String from;
    private String to;
    private String airlineName;
    private String airlineCode;
    private String cabin;

    private String originCountry;
    private String originCountryCode;
    private String originCity;
    private String originCityCode;

    private String destinationCountry;
    private String destinationCountryCode;
    private String destinationCity;
    private String destinationCityCode;

    private String departureAirport;
    private String departureDate;

    private String arriveAirport;
    private String arriveDate;

    private String cost;
}
