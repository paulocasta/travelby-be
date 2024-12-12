package com.travelby.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "city")
public class City {

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
    private Country country;
}
