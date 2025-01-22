package com.example.projektpo.controller;

import com.example.projektpo.model.Message;
import com.example.projektpo.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;


@Controller
@RequestMapping("/")
public class MessageController {
    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @GetMapping("/greetemp")
    public String greetingsEmployee(Model model) {
        model.addAttribute("username", "JohnDoe");
        return "greetingsEmployee";
    }

    @GetMapping("/sendmessage")
    public String sendMessage() {
        System.out.println("Sending Message");
        return "send_message";
    }

    @PostMapping("/sendmessage")
    public String sendMessage(@RequestParam String content) {
        Message message = new Message(content);

        System.out.println("Message content: " + content);

        service.saveMessage(message);

        System.out.println("Message sent: " + content);

        return "greetingsEmployee";
    }
}
