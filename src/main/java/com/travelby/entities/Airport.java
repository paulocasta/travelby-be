package com.travelby.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "airport")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Getter
    private String name;

    @Column
    @Getter
    private String code;

    @OneToOne
    @Getter
    private City city;
}
