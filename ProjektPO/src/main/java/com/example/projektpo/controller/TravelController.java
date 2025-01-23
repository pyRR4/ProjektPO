package com.example.projektpo.controller;

import com.example.projektpo.model.Country;
import com.example.projektpo.model.Travel;
import com.example.projektpo.model.TravelStage;
import com.example.projektpo.service.CountryService;
import com.example.projektpo.service.OrganizerService;
import com.example.projektpo.service.TravelService;
import com.example.projektpo.service.TravelStageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class TravelController {
    private final OrganizerService organizerService;
    private final TravelService travelService;
    private final TravelStageService travelStageService;
    private final CountryService countryService;

    private final List<TravelStage> travelStages = new ArrayList<>();

    public TravelController(OrganizerService organizerService, TravelService travelService, TravelStageService travelStageService, CountryService countryService) {
        this.organizerService = organizerService;
        this.travelService = travelService;
        this.travelStageService = travelStageService;
        this.countryService = countryService;
    }

    @GetMapping("/greetings")
    public String greetings(Model model) {
        model.addAttribute("username", "Jacek");
        return "greetings";
    }

    @GetMapping("/register_travel")
    public String registerTravelPage(Model model) {
//        Travel travel = new Travel();
//        model.addAttribute("travel", travel);
        model.addAttribute("travel", travelStages);
        List<Country> countries = countryService.getAllCountries();
        model.addAttribute("countries", countries);
        return "register_travel";
    }

    @PostMapping("/register_travel")
    public String registerTravel(@ModelAttribute Travel travel) {

        System.out.println("ID: " + travel.getId());
        System.out.println("Client ID: " + travel.getClientId());
        System.out.println("Travel Statistic ID: " + travel.getTravelStatisticId());
        System.out.println("Stages: " + travel.getStages());


        Travel savedTravel = travelService.saveTravel(travel);

        for (TravelStage travelStage : travelStages) {
            travelStage.setTravel(savedTravel);
            travelStageService.saveStage(travelStage);
        }

        travelStages.clear();

        return "redirect:/greetings";
    }

    @GetMapping("/register_stage")
    public String registerStagePage(@RequestParam(required = false) Integer stageId, Model model) {
        TravelStage stage = stageId != null ? travelStageService.getStageById(stageId) : new TravelStage();
        model.addAttribute("stage", stage);
        List<Country> countries = countryService.getAllCountries();
        model.addAttribute("countries", countries);
        //model.addAttribute("travel", 1);
        return "register_stage";
    }

    @PostMapping("/register_stage")
    public String registerStage(@ModelAttribute TravelStage stage/*, @RequestParam Integer travelId*/) {
        //Travel travel = travelService.getTravelById(travelId);

        //stage.setTravel(travel);
        System.out.println(stage.getDestinationCountry());

        //travelStageService.saveStage(stage);
        travelStages.add(stage);

        return "redirect:/register_travel";
    }

    @GetMapping("/delete_stage")
    public String deleteStage(@RequestParam int stageId) {
        travelStages.remove(stageId);
        return "redirect:/register_travel";
    }

    @GetMapping("/edit_stage")
    public String editStage(@RequestParam int stageId, Model model) {
        TravelStage stage = travelStages.get(stageId);
        model.addAttribute("stage", stage);
        model.addAttribute("stageId", stageId);
        List<Country> countries = countryService.getAllCountries();
        model.addAttribute("countries", countries);
        return "edit_stage";
    }

    @PostMapping("/edit_stage")
    public String editStage(@ModelAttribute TravelStage stage, @RequestParam int stageId) {
        travelStages.set(stageId, stage);
        return "redirect:/register_travel";
    }

}


