package com.example.projektpo.service.implementation;

import com.example.projektpo.dto.WarningDTO;
import com.example.projektpo.entity.Country;
import com.example.projektpo.entity.Warning;
import com.example.projektpo.exception.CountryNotFound;
import com.example.projektpo.exception.WarningNotFound;
import com.example.projektpo.mappers.WarningMapper;
import com.example.projektpo.repository.CountryRepository;
import com.example.projektpo.repository.WarningRepository;
import com.example.projektpo.service.contract.WarningServiceContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WarningServiceImplementation implements WarningServiceContract {

    private final WarningRepository warningRepository;
    private final WarningMapper warningMapper;
    private final CountryRepository countryRepository;
    private final static Logger logger = LoggerFactory.getLogger(WarningServiceImplementation.class);

    @Autowired
    public WarningServiceImplementation(
            WarningRepository warningRepository,
            WarningMapper warningMapper,
            CountryRepository countryRepository) {
        this.warningRepository = warningRepository;
        this.warningMapper = warningMapper;
        this.countryRepository = countryRepository;
    }


    @Override
    public List<WarningDTO> getAllWarnings() {
        List<Warning> warnings = warningRepository.findAll();

        return warnings.stream()
                .map(warningMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public void createWarning(WarningDTO warningDTO) {
        Warning warning = warningMapper.toEntity(warningDTO);

        logger.debug(warning.toString());

        Country country = countryRepository.findByCode(warningDTO.countryCode())
                .orElseThrow(() -> new CountryNotFound(warningDTO.countryCode()));

        warning.setCountry(country);
        warning.setId(null);

        logger.debug(warning.toString());

        warningRepository.save(warning);
    }

    @Override
    @Transactional
    public void deleteWarning(int id) {
        if(!warningRepository.existsById(id)) {
            throw new WarningNotFound(id);
        }

        warningRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateWarning(WarningDTO warningDTO) {
        Warning existingWarning = warningRepository.findByCountryCode(warningDTO.countryCode())
                .orElseThrow(() -> new WarningNotFound(warningDTO.countryCode()));

        existingWarning.setDescription(warningDTO.description());

        warningRepository.save(existingWarning);
    }
}
