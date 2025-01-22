package com.example.projektpo.controller;


import com.example.projektpo.dto.ConsulateDTO;
import com.example.projektpo.dto.CountryDTO;
import com.example.projektpo.exception.ConsulateNotFound;
import com.example.projektpo.service.contract.ConsulateServiceContract;
import com.example.projektpo.service.contract.CountryServiceContract;
import com.example.projektpo.service.implementation.ConsulateServiceImplementation;
import com.example.projektpo.service.implementation.CountryServiceImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/consulates")
public class ConsulateController {

    private final ConsulateServiceContract consulateService;
    private final CountryServiceContract countryService;
    public static final Logger logger = LoggerFactory.getLogger(ConsulateController.class);

    @Autowired
    public ConsulateController(
            ConsulateServiceImplementation consulateService,
            CountryServiceImplementation countryService
    ) {
        this.consulateService = consulateService;
        this.countryService = countryService;
    }


    @GetMapping
    public String getConsulates(Model model) {
        List<ConsulateDTO> consulates = consulateService.getAllConsulates();
        List<CountryDTO> countries = countryService.getAllCountries();

        model.addAttribute("consulates", consulates);
        model.addAttribute("countries", countries);
        model.addAttribute("consulate", new ConsulateDTO());

        return "crud_layout";
    }

    @GetMapping("/filter")
    public String filterConsulates(@ModelAttribute String countryName, Model model) {
        List<ConsulateDTO> consulates = consulateService.getAllConsulatesByCountry(countryName);

        model.asMap().put("consulates", consulates);

        return "redirect:/consulates";
    }

    @PostMapping("/create")
    public String createConsulate(@ModelAttribute ConsulateDTO consulateDTO) {
        logger.debug(consulateDTO.toString());
        consulateService.createConsulate(consulateDTO);

        return "redirect:/consulates";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        try {
            ConsulateDTO consulateDTO = consulateService.getConsulateById(id);
            List<CountryDTO> countries = countryService.getAllCountries();

            model.addAttribute("consulate", consulateDTO);
            model.addAttribute("countries", countries);
        } catch (ConsulateNotFound e) {
            return "redirect:/consulates";
        }
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String updateConsulate(@PathVariable int id, @ModelAttribute ConsulateDTO consulateDTO, Model model) {
        consulateService.updateConsulate(consulateDTO);

        return "redirect:/consulates";
    }

    @PostMapping("/delete/{id}")
    public String deleteConsulate(@PathVariable int id) {
        try {
            consulateService.deleteConsulate(id);
        } catch (ConsulateNotFound e) {

        }
        return "redirect:/consulates";
    }
}
