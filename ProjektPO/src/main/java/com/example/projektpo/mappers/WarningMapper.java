package com.example.projektpo.mappers;

import com.example.projektpo.dto.WarningDTO;
import com.example.projektpo.entity.Warning;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.lang.reflect.Type;
import java.util.List;

@Mapper(componentModel = "spring")
public interface WarningMapper {

    Gson gson = new Gson();

    @Mapping(target = "countryCode", source = "country.code")
    WarningDTO toDTO(Warning warning);

    @Mapping(target = "country", ignore = true)
    Warning toEntity(WarningDTO warningDTO);

    default List<WarningDTO> fromJson(String json) {
        Type warningListType = new TypeToken<List<WarningDTO>>() {}.getType();
        List<WarningDTO> warnings = gson.fromJson(json, warningListType);

        return warnings;
    }
}
