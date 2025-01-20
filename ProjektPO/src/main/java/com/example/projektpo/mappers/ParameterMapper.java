package com.example.projektpo.mappers;

import com.example.projektpo.dto.ParameterDTO;
import com.example.projektpo.entity.Parameter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParameterMapper {

    ParameterDTO toDTO(Parameter parameter);

    Parameter toEntity(ParameterDTO parameterDTO);
}
