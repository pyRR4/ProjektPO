package com.example.projektpo.repository;

import com.example.projektpo.model.Travel;
import com.example.projektpo.model.TravelStage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelStageRepository extends JpaRepository<TravelStage, Integer> {
}

