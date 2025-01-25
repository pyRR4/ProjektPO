package com.example.projektpo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {

    @GetMapping("/consulates")
    public String consulatesPage() {
        return "/consulates";
    }

    @GetMapping("/consulates/edit/{id}")
    public String editConsulatePage(@PathVariable int id) {
        return "/consulates/edit";
    }
}

