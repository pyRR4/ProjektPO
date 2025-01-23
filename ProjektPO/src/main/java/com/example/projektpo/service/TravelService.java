package com.example.projektpo.service;

import com.example.projektpo.model.Travel;
import com.example.projektpo.repository.TravelRepository;
import org.springframework.stereotype.Service;

@Service
public class TravelService {
    private final TravelRepository repository;

    public TravelService(TravelRepository repository) {
        this.repository = repository;
    }

    public Travel saveTravel(Travel travel) {
        return repository.save(travel);
    }

    public Travel getTravelById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Travel not found"));
    }
}

