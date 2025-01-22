package com.example.projektpo.oldentities;

import org.springframework.data.repository.CrudRepository;

public interface TravelStageRepository extends CrudRepository<TravelStage, Integer> {
    TravelStage findById(int id);

    
}
