package com.example.projektpo;

import org.springframework.data.repository.CrudRepository;

public interface ConsulateRepository extends CrudRepository<Consulate, Integer> {
    Consulate findById(int id);

    Consulate findAllByCountryId(int countryId);
}
