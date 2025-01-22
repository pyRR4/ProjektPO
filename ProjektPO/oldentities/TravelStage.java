package com.example.projektpo.oldentities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class TravelStage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private int userId;

    @Column(nullable = false)
    private int travelId;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private String startDate;

    @Column(nullable = false)
    private String endDate;

    @Column(nullable = false)
    private String destinationCity;

    @Column(nullable = false)
    private String destinationAddress;

    @Column(nullable = false)
    private String destinationCountry;

    @Column(nullable = false)
    private String organizer;
}
