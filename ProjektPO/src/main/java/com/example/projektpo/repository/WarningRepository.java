package com.example.projektpo.repository;

import com.example.projektpo.entity.Warning;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WarningRepository extends JpaRepository<Warning, Integer> {
    Optional<Warning> findById(int id);

    boolean existsById(int id);
}
