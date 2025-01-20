package com.example.projektpo.mappers;

import com.example.projektpo.dto.ConsulateDTO;
import com.example.projektpo.entity.Consulate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConsulateMapper {

    @Mapping(target = "countryId", source = "country.id")
    ConsulateDTO toDTO(Consulate consulate);

    @Mapping(target = "country", ignore = true)
    Consulate toEntity(ConsulateDTO consulateDTO);
}
