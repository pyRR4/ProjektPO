package com.example.projektpo.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity(
        name = "countries"
)
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Consulate> consulates;
}
