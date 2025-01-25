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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConsulateServiceImplementation implements ConsulateServiceContract {

    private final ConsulateRepository consulateRepository;
    private final ConsulateMapper consulateMapper;
    private final CountryRepository countryRepository;

    public static final Logger logger = LoggerFactory.getLogger(ConsulateServiceContract.class);

    @Autowired
    public ConsulateServiceImplementation(
            ConsulateRepository consulateRepository,
            ConsulateMapper consulateMapper,
            CountryRepository countryRepository) {
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

        Country country = countryRepository.findByName(consulateDTO.countryName())
                        .orElseThrow(() -> new CountryNotFound(consulateDTO.countryName()));

        consulate.setCountry(country);

        Consulate savedConsulate = consulateRepository.save(consulate);

        return consulateMapper.toDTO(savedConsulate);
    }

    @Override
    public ConsulateDTO updateConsulate(ConsulateDTO consulateDTO) {
        Consulate existingConsulate = consulateRepository.findById(consulateDTO.id())
                .orElseThrow(() -> new ConsulateNotFound(consulateDTO.code()));

        Country consulateDTOCountry = countryRepository.findByName(consulateDTO.countryName())
                .orElseThrow(() -> new CountryNotFound(consulateDTO.countryName()));

        existingConsulate.setCode(consulateDTO.code());
        existingConsulate.setCountry(consulateDTOCountry);

        return consulateMapper.toDTO(consulateRepository.save(existingConsulate));
    }


    @Transactional
    @Override
    public void deleteConsulate(int consulateId) {
        consulateRepository.findById(consulateId).ifPresentOrElse(
                consulate -> {
                    consulateRepository.delete(consulate);
                    consulateRepository.flush();
                    logger.debug("Consulate deleted successfully: " + consulateId);
                },
                () -> {
                    logger.error("Consulate not found: " + consulateId);
                    throw new ConsulateNotFound(consulateId);
                }
        );
    }
}
