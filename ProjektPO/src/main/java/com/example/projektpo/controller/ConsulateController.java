package com.example.projektpo.controller;

import com.example.projektpo.dto.ConsulateDTO;
import com.example.projektpo.exception.ConsulateNotFound;
import com.example.projektpo.service.contract.ConsulateServiceContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consulates")
public class ConsulateController {

    private final ConsulateServiceContract consulateService;
    public static final Logger logger = LoggerFactory.getLogger(ConsulateController.class);

    @Autowired
    public ConsulateController(
            ConsulateServiceContract consulateService
    ) {
        this.consulateService = consulateService;
    }

    @GetMapping
    public ResponseEntity<List<ConsulateDTO>> getAllConsulates() {
        List<ConsulateDTO> consulates = consulateService.getAllConsulates();
        return ResponseEntity
                .ok()
                .body(consulates);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ConsulateDTO>> filterConsulates(@RequestParam String countryName) {
        List<ConsulateDTO> consulates = consulateService.getAllConsulatesByCountry(countryName);
        return ResponseEntity
                .ok()
                .body(consulates);
    }

    @PostMapping
    public ResponseEntity<Void> createConsulate(@RequestBody ConsulateDTO consulateDTO) {
        logger.debug(consulateDTO.toString());
        consulateService.createConsulate(consulateDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsulateDTO> getConsulateById(@PathVariable int id) {
        try {
            ConsulateDTO consulateDTO = consulateService.getConsulateById(id);
            return ResponseEntity
                    .ok()
                    .body(consulateDTO);
        } catch (ConsulateNotFound e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateConsulate(@PathVariable int id, @RequestBody ConsulateDTO consulateDTO) {
        try {
            consulateService.updateConsulate(consulateDTO);
            return ResponseEntity
                    .noContent()
                    .build();
        } catch (ConsulateNotFound e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsulate(@PathVariable int id) {
        try {
            consulateService.deleteConsulate(id);
            return ResponseEntity
                    .noContent()
                    .build();
        } catch (ConsulateNotFound e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }
}
