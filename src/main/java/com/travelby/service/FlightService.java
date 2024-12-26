package com.travelby.service;

import com.travelby.dto.FlightDTO;
import com.travelby.entities.City;
import com.travelby.entities.Flight;
import com.travelby.repository.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Service
public class FlightService {

    private final FlightRepository flightRepository;
    public List<FlightDTO> getAllFlights(String origin, String destination, String departureDate, String returnDate) {
        List<Flight> flights = flightRepository.findByOriginNameAndDestinationName(origin, destination);

        return transformFlights(flights,departureDate, returnDate);
    }

    private static List<FlightDTO> transformFlights(List<Flight> flights, String departureDate, String returnDate) {
        if (flights.isEmpty()) {
            return Collections.emptyList();
        }
        List<FlightDTO> dtos = new ArrayList<>();

        for (Flight flight : flights) {
            dtos.add(createFlight(flight, departureDate, returnDate));
        }
        return dtos;
    }

    private static FlightDTO createFlight(Flight flight, String departureDate, String returnDate) {
        City origin = flight.getOrigin();
        City destination = flight.getDestination();

        FlightDTO dto = FlightDTO.builder()
                .from(origin.getName())
                .to(destination.getName())
                .airlineName(flight.getAirline().getName())
                .airlineCode(flight.getAirline().getCode())
                .cabin(flight.getCabinClass())
                .originCountry(origin.getCountry().getName())
                .originCountryCode(origin.getCountry().getCode())
                .originCity(origin.getName())
                .originCityCode(origin.getCode())
                .destinationCountry(destination.getCountry().getName())
                .destinationCountryCode(destination.getCountry().getCode())
                .destinationCity(destination.getName())
                .destinationCityCode(destination.getCode())
                .departureAirport(flight.getDepartureAirport().getName())
                .departureCode(flight.getDepartureAirport().getCode())
                .departureDate(departureDate)
                .arriveAirport(flight.getArrivalAirport().getName())
                .arriveCode(flight.getArrivalAirport().getCode())
                .arriveDate(returnDate)
                .cost(calculateCost())
                .build();
        return dto;
    }

    private static int calculateCost() {
        int maxPrice = 2000;
        int minPrice = 1000;
        Random random = new Random();
        return random.nextInt(maxPrice - minPrice) + minPrice;
    }
}
