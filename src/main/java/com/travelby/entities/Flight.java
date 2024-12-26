package com.travelby.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @Getter
    private City origin;

    @OneToOne
    @Getter
    private City destination;

    @OneToOne
    @Getter
    private Airline airline;

    @OneToOne
    @Getter
    private Airport departureAirport;

    @OneToOne
    @Getter
    private Airport arrivalAirport;

    @Column(name = "cabin_class")
    @Getter
    private String cabinClass;
}
