package com.example.projektpo;

import org.springframework.data.repository.CrudRepository;

public interface AdministratorRepository extends CrudRepository<Administrator, Integer> {
    Administrator findById(int id);
}
