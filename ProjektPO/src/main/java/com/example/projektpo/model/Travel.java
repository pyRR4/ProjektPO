package com.example.projektpo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Travel {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getTravelStatisticId() {
        return travelStatisticId;
    }

    public void setTravelStatisticId(Integer travelStatisticId) {
        this.travelStatisticId = travelStatisticId;
    }

    public List<TravelStage> getStages() {
        return stages;
    }

    public void setStages(List<TravelStage> stages) {
        this.stages = stages;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(nullable = false)
    private Integer clientId = 24;

    @Column(nullable = false)
    private Integer travelStatisticId = 196;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TravelStage> stages = new ArrayList<>();
}

