package com.example.projektpo.repository;

import com.example.projektpo.entity.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParameterRepository extends JpaRepository<Parameter, Long> {

    Optional<Parameter> findByName(String name);

    Optional<Parameter> findById(int id);

    boolean existsById(int id);
}
