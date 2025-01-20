package com.example.projektpo.dto;

import java.math.BigDecimal;
import java.util.Date;

public record ParameterDTO (
        int id,
        String name,
        BigDecimal value,
        Date created_at,
        Date updated_at
) {

}