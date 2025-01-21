package com.example.projektpo.exception;

public class CountryNotFound extends RuntimeException {
    public CountryNotFound(String name) {
        super(String.format("Country with code %s not found", name));
    }

    public CountryNotFound(int id) {
        super(String.format("Country with id %s not found", id));
    }
}
