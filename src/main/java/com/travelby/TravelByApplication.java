package com.travelby;

import com.travelby.entities.Airline;
import com.travelby.entities.Airport;
import com.travelby.entities.City;
import com.travelby.entities.Flight;
import com.travelby.repository.AirlineRepository;
import com.travelby.repository.AirportRepository;
import com.travelby.repository.CityRepository;
import com.travelby.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.swing.text.html.Option;
import java.util.*;

@SpringBootApplication
public class TravelByApplication {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private AirlineRepository airlineRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AirportRepository airportRepository;


    @Value("${travelby.should.import.flight-data}")
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
        List<Airport> airports = airportRepository.findAll();

        if (cities.isEmpty() || airlines.isEmpty()) {
            return Collections.emptyList();
        }
        List<Flight> flights = new ArrayList<>();
        createFlights(cities, airlines, flights, airports);

        return flights;
    }

    private void createFlights(List<City> cities, List<Airline> airlines, List<Flight> flights, List<Airport> airports) {
        int pointAC = 0;
        int pointBC = cities.size()-1;
        Random random = new Random();
        int max = airlines.size()-1;
        int min = 0;
        while (pointAC < pointBC) {
            City origin = cities.get(pointAC);
            City destination = cities.get(pointBC);

            Optional<Airport> departureAirport = airports.stream().filter(airport -> airport.getCity().equals(origin)).findFirst();
            Optional<Airport> arrivalAirport = airports.stream().filter(airport -> airport.getCity().equals(destination)).findFirst();

            for (CabinClass cc : CabinClass.values()) {
                Flight flight = Flight.builder()
                        .origin(origin)
                        .destination(destination)
                        .cabinClass(cc.name)
                        .airline(airlines.get(random.nextInt(max - min + 1) + min))
                        .departureAirport(departureAirport.orElse(null))
                        .arrivalAirport(arrivalAirport.orElse(null))
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