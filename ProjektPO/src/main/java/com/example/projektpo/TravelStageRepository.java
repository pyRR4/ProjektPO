package com.example.projektpo;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.repository.CrudRepository;

public interface TravelStageRepository extends CrudRepository<TravelStage, Integer> {
    TravelStage findById(int id);

    
}
