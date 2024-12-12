package com.travelby;

import com.travelby.entities.Airline;
import com.travelby.entities.City;
import com.travelby.entities.Flight;
import com.travelby.repository.AirlineRepository;
import com.travelby.repository.CityRepository;
import com.travelby.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class TravelByApplication {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private AirlineRepository airlineRepository;
    @Autowired
    private FlightRepository flightRepository;

    @Value("${traveby.should.import.flight-data}")
    private boolean importFlightData;

    public TravelByApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(TravelByApplication.class, args);
    }

    @Bean
    public CommandLineRunner demoData() {
        return args -> {
            if (importFlightData) {
                List<Flight> flights = generateFlights();
                if (!flights.isEmpty())
                    flightRepository.saveAll(flights);
            }
        };
    }

    private List<Flight> generateFlights() {
        List<City> cities = cityRepository.findAll();
        List<Airline> airlines = airlineRepository.findAll();

        if (cities.isEmpty() || airlines.isEmpty()) {
            return Collections.emptyList();
        }
        List<Flight> flights = new ArrayList<>();
        createFlights(cities, airlines, flights);

        return flights;
    }

    private void createFlights(List<City> cities, List<Airline> airlines, List<Flight> flights) {
        int pointAC = 0;
        int pointBC = cities.size()-1;
        Random random = new Random();
        int max = airlines.size()-1;
        int min = 0;
        while (pointAC < pointBC) {
            City origin = cities.get(pointAC);
            City destination = cities.get(pointBC);

            for (CabinClass cc : CabinClass.values()) {
                Flight flight = Flight.builder()
                        .origin(origin)
                        .destination(destination)
                        .cabinClass(cc.name)
                        .airline(airlines.get(random.nextInt(max - min + 1) + min))
                        .build();
                flights.add(flight);
            }
            pointBC--;

            if (pointAC == pointBC) {
                pointAC++;
                pointBC = cities.size()-1;
            }
        }
    }

}

enum CabinClass {
    ECONOMY("economy"),
    BUSINESS("business"),
    FIRST_CLASS("first class");

    final String name;

    CabinClass(String name) {
        this.name = name;
    }
}