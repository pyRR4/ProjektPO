package com.example.projektpo.service.contract;

import com.example.projektpo.dto.CountryDTO;

import java.util.List;

public interface CountryServiceContract {

    List<CountryDTO> getAllCountries();

    CountryDTO getCountryById(int id);

    CountryDTO getCountryByName(String name);

    CountryDTO createCountry(CountryDTO consulateDTO);

    CountryDTO updateCountry(CountryDTO consulateDTO);

    void deleteCountry(int id);
}
