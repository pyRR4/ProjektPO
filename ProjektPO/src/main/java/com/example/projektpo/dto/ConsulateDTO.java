package com.example.projektpo.dto;

public record ConsulateDTO(
        Integer id,
        String code,
        String countryName
) {

    public ConsulateDTO() {
        this(0, "", "");
    }
}
