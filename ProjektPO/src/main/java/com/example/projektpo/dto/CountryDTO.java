package com.example.projektpo.dto;

import java.util.List;

public record CountryDTO(
        Integer id,
        String name,
        String code,
        List<ConsulateDTO> consulates
) {
}
