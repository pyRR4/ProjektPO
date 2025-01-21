package com.example.projektpo.service.implementation;

import com.example.projektpo.dto.ParameterDTO;
import com.example.projektpo.entity.Parameter;
import com.example.projektpo.exception.ParameterNotFound;
import com.example.projektpo.mappers.ParameterMapper;
import com.example.projektpo.repository.ParameterRepository;
import com.example.projektpo.service.contract.ParameterServiceContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParameterServiceImplementation implements ParameterServiceContract {

    private final ParameterRepository parameterRepository;
    private final ParameterMapper parameterMapper;

    @Autowired
    public ParameterServiceImplementation(
            ParameterRepository parameterRepository,
            ParameterMapper parameterMapper
    ) {
        this.parameterRepository = parameterRepository;
        this.parameterMapper = parameterMapper;
    }

    @Override
    public ParameterDTO getParameter(int id) {
        Parameter parameter = parameterRepository.findById(id)
                .orElseThrow(() -> new ParameterNotFound(id));

        return parameterMapper.toDTO(parameter);
    }

    @Override
    public ParameterDTO getParameterByName(String name) {
        Parameter parameter = parameterRepository.findByName(name)
                .orElseThrow(() -> new ParameterNotFound(name));

        return parameterMapper.toDTO(parameter);
    }

    @Override
    public List<ParameterDTO> getAllParameters() {
        List<Parameter> parameters = parameterRepository.findAll();

        return parameters.stream()
                .map(parameterMapper::toDTO)
                .toList();
    }

    @Override
    public ParameterDTO updateParameter(ParameterDTO parameter) {
        Parameter existingParameter = parameterRepository.findById(parameter.id())
                .orElseThrow(() -> new ParameterNotFound(parameter.id()));

        existingParameter.setValue(parameter.value());
        existingParameter.setUpdated_at(LocalDateTime.now());

        return parameterMapper.toDTO(parameterRepository.save(existingParameter));
    }
}
