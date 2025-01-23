package com.example.projektpo.service;

import com.example.projektpo.model.Organizer;
import com.example.projektpo.repository.OrganizerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizerService {
    private final OrganizerRepository repository;

    public OrganizerService(OrganizerRepository repository) {
        this.repository = repository;
    }

    public List<Organizer> getAllOrganizers() {
        return repository.findAll();
    }
}

