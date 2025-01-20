package com.example.projektpo.repository;


import com.example.projektpo.entity.Consulate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsulateRepository extends JpaRepository<Consulate, Long> {

    public List<Consulate> findAllByCountryName(String country);

    public Optional<Consulate> findById(int id);

    public boolean existsById(int id);

    public void deleteById(int id);
}
