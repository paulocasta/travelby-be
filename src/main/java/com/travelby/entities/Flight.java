package com.travelby.entities;

import jakarta.persistence.*;
import lombok.Builder;

@Builder(toBuilder = true)
@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private City origin;

    @OneToOne
    private City destination;

    @OneToOne
    private Airline airline;

    @Column(name = "cabin_class")
    private String cabinClass;
}
