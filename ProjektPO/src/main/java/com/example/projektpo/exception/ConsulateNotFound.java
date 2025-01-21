package com.example.projektpo.exception;

public class ConsulateNotFound extends RuntimeException {
    public ConsulateNotFound(String name) {
        super(String.format("Consulate with code %s not found", name));
    }

    public ConsulateNotFound(int id) {
        super(String.format("Consulate with id %s not found", id));
    }
}
