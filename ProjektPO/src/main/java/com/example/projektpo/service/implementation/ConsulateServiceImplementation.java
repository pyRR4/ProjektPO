package com.example.projektpo.service.implementation;

import com.example.projektpo.dto.ConsulateDTO;
import com.example.projektpo.entity.Consulate;
import com.example.projektpo.entity.Country;
import com.example.projektpo.exception.ConsulateNotFound;
import com.example.projektpo.exception.CountryNotFound;
import com.example.projektpo.mappers.ConsulateMapper;
import com.example.projektpo.repository.ConsulateRepository;
import com.example.projektpo.repository.CountryRepository;
import com.example.projektpo.service.contract.ConsulateServiceContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsulateServiceImplementation implements ConsulateServiceContract {

    private final ConsulateRepository consulateRepository;
    private final ConsulateMapper consulateMapper;
    private final CountryRepository countryRepository;

    @Autowired
    public ConsulateServiceImplementation(
            final ConsulateRepository consulateRepository,
            final ConsulateMapper consulateMapper,
            final CountryRepository countryRepository) {
        this.consulateRepository = consulateRepository;
        this.consulateMapper = consulateMapper;
        this.countryRepository = countryRepository;
    }


    @Override
    public List<ConsulateDTO> getAllConsulates() {
        List<Consulate> consulates = consulateRepository.findAll();

        return consulateMapper.toDTOList(consulates);
    }

    @Override
    public List<ConsulateDTO> getAllConsulatesByCountry(String name) {
        List<Consulate> consulates = consulateRepository.findAllByCountryName(name);

        return consulateMapper.toDTOList(consulates);
    }

    @Override
    public ConsulateDTO getConsulateById(int id) {
        Consulate consulate = consulateRepository.findById(id)
                .orElseThrow(() ->
                        new ConsulateNotFound(id)
                );

        return consulateMapper.toDTO(consulate);
    }

    @Override
    public ConsulateDTO createConsulate(ConsulateDTO consulateDTO) {
        Consulate consulate = consulateMapper.toEntity(consulateDTO);

        consulateRepository.save(consulate);

        return consulateMapper.toDTO(consulate);
    }

    @Override
    public ConsulateDTO updateConsulate(ConsulateDTO consulateDTO) {
        Consulate existingConsulate = consulateRepository.findById(consulateDTO.id())
                .orElseThrow(() -> new ConsulateNotFound(consulateDTO.name()));

        Country consulateDTOCountry = countryRepository.findById(consulateDTO.countryId())
                .orElseThrow(() -> new CountryNotFound(consulateDTO.countryId()));

        existingConsulate.setName(consulateDTO.name());
        existingConsulate.setCountry(consulateDTOCountry);

        return consulateMapper.toDTO(consulateRepository.save(existingConsulate));
    }


    @Override
    public void deleteConsulate(int consulateId) {
        if (!consulateRepository.existsById(consulateId)) {
            throw new ConsulateNotFound(consulateId);
        }

        consulateRepository.deleteById(consulateId);
    }
}
