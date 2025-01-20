package com.example.projektpo.dto;

import java.util.List;

public record CountryDTO(
        int id,
        String name,
        List<ConsulateDTO> consulates
) {
}
