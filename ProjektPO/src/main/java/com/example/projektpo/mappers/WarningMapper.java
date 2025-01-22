package com.example.projektpo.mappers;

import com.example.projektpo.dto.WarningDTO;
import com.example.projektpo.entity.Warning;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WarningMapper {

    @Mapping(target = "countryName", source = "country.name")
    WarningDTO toDTO(Warning warning);

    @Mapping(target = "country", ignore = true)
    Warning toEntity(WarningDTO warningDTO);
}
