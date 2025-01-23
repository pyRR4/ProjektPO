package com.example.projektpo.service.contract;

import com.example.projektpo.dto.ParameterDTO;

import java.util.List;

public interface ParameterServiceContract {

    int getParameterValueByName(String name);

    ParameterDTO getParameterByName(String name);

    List<ParameterDTO> getAllParameters();

    ParameterDTO updateParameter(ParameterDTO parameter);
}
