package com.travelby.controller;

import com.travelby.dto.FlightDTO;
import com.travelby.service.FlightService;
import lombok.AllArgsConstructor;
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
    public List<FlightDTO> getFlights() {
        return flightService.getAllFlights();
    }
}
