package com.example.projektpo.exception;

public class WarningNotFound extends RuntimeException {
  public WarningNotFound(int id) {
    super(String.format("Warning with id %s not found", id));
  }
}
