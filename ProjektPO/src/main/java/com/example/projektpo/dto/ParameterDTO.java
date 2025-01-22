package com.example.projektpo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ParameterDTO (
        Integer id,
        String name,
        BigDecimal value,
        LocalDateTime created_at,
        LocalDateTime updated_at
) {
}