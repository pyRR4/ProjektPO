package com.example.projektpo.repository;


import com.example.projektpo.entity.Consulate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsulateRepository extends JpaRepository<Consulate, Integer> {

    List<Consulate> findAllByCountryName(String country);

    Optional<Consulate> findById(int id);

    boolean existsById(int id);

    void deleteById(int id);
}
