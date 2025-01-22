package com.example.projektpo.service;

import com.example.projektpo.model.Message;
import com.example.projektpo.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public Message saveMessage(Message message) {
        return repository.save(message);
    }
}

