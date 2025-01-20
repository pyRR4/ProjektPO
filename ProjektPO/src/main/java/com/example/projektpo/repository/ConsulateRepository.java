package com.example.projektpo.repository;


import com.example.projektpo.entity.Consulate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsulateRepository extends JpaRepository<Consulate, Long> {

}
