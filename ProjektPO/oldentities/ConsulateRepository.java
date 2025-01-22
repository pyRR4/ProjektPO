package com.example.projektpo.oldentities;

import org.springframework.data.repository.CrudRepository;

public interface ConsulateRepository extends CrudRepository<Consulate, Integer> {
    Consulate findById(int id);

    Consulate findAllByCountryId(int countryId);
}
