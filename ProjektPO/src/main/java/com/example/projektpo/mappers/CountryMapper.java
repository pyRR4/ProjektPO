package com.example.projektpo.mappers;

import com.example.projektpo.dto.CountryDTO;
import com.example.projektpo.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    @Mapping(target = "consulates", source = "consulates")
    CountryDTO toDTO(Country country);

    Country toEntity(CountryDTO countryDTO);
}
