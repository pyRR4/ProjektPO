package com.example.projektpo.controller;


import com.example.projektpo.dto.ConsulateDTO;
import com.example.projektpo.service.contract.ConsulateServiceContract;
import com.example.projektpo.service.implementation.ConsulateServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller("/consulates")
public class ConsulateController {

    private final ConsulateServiceContract consulateService;

    @Autowired
    public ConsulateController(ConsulateServiceImplementation consulateService) {
        this.consulateService = consulateService;
    }


    @GetMapping
    public String getConsulates(Model model) {
        List<ConsulateDTO> consulates = consulateService.getAllConsulates();


        return "";
    }

}
