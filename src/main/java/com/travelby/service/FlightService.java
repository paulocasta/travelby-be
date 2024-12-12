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

@AllArgsConstructor
@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public List<FlightDTO> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();

        return transformFlights(flights);
    }

    private static List<FlightDTO> transformFlights(List<Flight> flights) {
        if (flights.isEmpty()) {
            return Collections.emptyList();
        }
        List<FlightDTO> dtos = new ArrayList<>();

        for (Flight f : flights) {
            City origin = f.getOrigin();
            City destination = f.getDestination();
            dtos.add(FlightDTO.builder()
                    .from(origin.getName())
                    .to(destination.getName())
                    .airlineName(f.getAirline().getName())
                    .airlineCode(f.getAirline().getCode())
                    .originCountry(origin.getCountry().getName())
                    .originCountryCode(origin.getCountry().getCode())
                    .originCity(origin.getName())
                    .originCityCode(origin.getCode())
                    .destinationCountry(destination.getCountry().getName())
                    .destinationCountryCode(destination.getCountry().getCode())
                    .destinationCity(destination.getName())
                    .destinationCityCode(destination.getCode())
                    .cabin(f.getCabinClass())
                    .build());
        }
        return dtos;
    }
}
