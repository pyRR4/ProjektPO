package com.example.projektpo.service;

import com.example.projektpo.model.TravelStage;
import com.example.projektpo.repository.TravelStageRepository;
import org.springframework.stereotype.Service;

@Service
public class TravelStageService {
    private final TravelStageRepository repository;

    public TravelStageService(TravelStageRepository repository) {
        this.repository = repository;
    }

    public TravelStage saveStage(TravelStage stage) {
        return repository.save(stage);
    }

    public TravelStage getStageById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteStage(Integer id) {
        repository.deleteById(id);
    }
}

