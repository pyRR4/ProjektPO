package com.example.projektpo.repository;

import com.example.projektpo.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findByName(String name);

    Optional<Country> findById(int id);
}
