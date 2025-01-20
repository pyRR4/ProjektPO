package com.example.projektpo.service.implementation;

import com.example.projektpo.dto.CountryDTO;
import com.example.projektpo.entity.Country;
import com.example.projektpo.exception.CountryNotFound;
import com.example.projektpo.mappers.CountryMapper;
import com.example.projektpo.repository.CountryRepository;
import com.example.projektpo.service.contract.CountryServiceContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImplementation implements CountryServiceContract {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Autowired
    public CountryServiceImplementation(
            final CountryRepository countryRepository,
            final CountryMapper countryMapper
    ) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    @Override
    public List<CountryDTO> getAllCountries() {
        List<Country> countries = countryRepository.findAll();

        return countries.stream()
                .map(countryMapper::toDTO)
                .toList();
    }

    @Override
    public CountryDTO getCountryById(int id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFound(id));

        return countryMapper.toDTO(country);
    }

    @Override
    public CountryDTO getCountryByName(String name) {
        Country country = countryRepository.findByName(name)
                .orElseThrow(() -> new CountryNotFound(name));

        return countryMapper.toDTO(country);
    }

    @Override
    public CountryDTO createCountry(CountryDTO consulateDTO) {
        Country country = countryMapper.toEntity(consulateDTO);

        return countryMapper.toDTO(countryRepository.save(country));
    }

    @Override
    public CountryDTO updateCountry(CountryDTO countryDTO) {
        Country existingCountry = countryRepository.findById(countryDTO.id())
                .orElseThrow(() -> new CountryNotFound(countryDTO.name()));

        existingCountry.setName(countryDTO.name());

        return countryMapper.toDTO(countryRepository.save(existingCountry));
    }

    @Override
    public void deleteCountry(int id) {
        if(!countryRepository.existsById(id)) {
            throw new CountryNotFound(id);
        }
    }
}
