package com.example.projektpo.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity(
        name = "parameters"
)
public class Parameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal value;

    @Column(nullable = false)
    private Date updated_at;

    @Column(nullable = false)
    private Date created_at;
}
