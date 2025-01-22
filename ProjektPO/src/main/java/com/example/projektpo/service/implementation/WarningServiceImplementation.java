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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarningServiceImplementation implements WarningServiceContract {

    private final WarningRepository warningRepository;
    private final WarningMapper warningMapper;
    private final CountryRepository countryRepository;

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
    public WarningDTO createWarning(WarningDTO warningDTO) {
        Warning warning = warningMapper.toEntity(warningDTO);

        Country country = countryRepository.findByName(warningDTO.countryName())
                .orElseThrow(() -> new CountryNotFound(warningDTO.countryName()));

        warning.setCountry(country);

        Warning savedWarning = warningRepository.save(warning);

        return warningMapper.toDTO(savedWarning);
    }

    @Override
    public void deleteWarning(int id) {
        if(!warningRepository.existsById(id)) {
            throw new WarningNotFound(id);
        }

        warningRepository.deleteById(id);
    }

    @Override
    public WarningDTO updateWarning(WarningDTO warningDTO) {
        Warning existingWarning = warningRepository.findById(warningDTO.id())
                .orElseThrow(() -> new WarningNotFound(warningDTO.id()));

        Country warningDTOCountry = countryRepository.findByName(warningDTO.countryName())
                .orElseThrow(() -> new CountryNotFound(warningDTO.countryName()));

        existingWarning.setCountry(warningDTOCountry);
        existingWarning.setDescription(warningDTO.description());

        return warningMapper.toDTO(warningRepository.save(existingWarning));
    }
}
