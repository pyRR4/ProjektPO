package com.example.externalsystemsimulation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WarningController {

    private final WarningRepository warningRepository;

    @Autowired
    public WarningController(WarningRepository warningRepository) {
        this.warningRepository = warningRepository;
    }

    @GetMapping("/warnings")
    public ResponseEntity<List<Warning>> getAllWarnings() {
        List<Warning> warnings = warningRepository.findAll();
        return ResponseEntity.ok(warnings);
    }
}
