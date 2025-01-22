package com.example.projektpo.oldentities;

import org.springframework.data.repository.CrudRepository;

public interface WarningRepository extends CrudRepository<Warning, Integer> {
    Warning findById(int id);

    Warning findAllByCountryId(int countryId);
}
