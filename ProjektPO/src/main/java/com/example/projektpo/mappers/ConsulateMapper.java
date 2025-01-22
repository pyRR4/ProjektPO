package com.example.projektpo.mappers;

import com.example.projektpo.dto.ConsulateDTO;
import com.example.projektpo.entity.Consulate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConsulateMapper {

    @Mapping(target = "countryName", source = "country.name")
    ConsulateDTO toDTO(Consulate consulate);

    @Mapping(target = "country", ignore = true)
    Consulate toEntity(ConsulateDTO consulateDTO);

    default List<ConsulateDTO> toDTOList(List<Consulate> consulates) {
        return consulates.stream()
                .map(this::toDTO)
                .toList();
    }
}
