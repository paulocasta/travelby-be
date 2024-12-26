package com.travelby.controller;

import com.travelby.dto.FlightDTO;
import com.travelby.service.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/flights")
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    public List<FlightDTO> getFlights(@Param("origin") String origin, @Param("destination") String destination,
                                      @Param("departureDate") String departureDate, @Param("returnDate") String returnDate) {

        return flightService.getAllFlights(origin, destination, departureDate, returnDate);
    }
}
