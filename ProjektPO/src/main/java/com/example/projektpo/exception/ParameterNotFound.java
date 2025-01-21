package com.example.projektpo.exception;

public class ParameterNotFound extends RuntimeException {
    public ParameterNotFound(String name) {
        super(String.format("Parameter with name %s not found", name));
    }

    public ParameterNotFound(int id) {
        super(String.format("Parameter with id %s not found", id));
    }
}
