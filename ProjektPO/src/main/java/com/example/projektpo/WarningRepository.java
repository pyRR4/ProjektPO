package com.example.projektpo;

import org.springframework.data.repository.CrudRepository;

public interface WarningRepository extends CrudRepository<Warning, Integer> {
    Warning findById(int id);

    Warning findAllByCountryId(int countryId);
}
