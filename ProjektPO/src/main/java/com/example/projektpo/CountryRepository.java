package com.example.projektpo;

import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Integer> {
    Country findByName(String name);

    Country findById(int id);
}
