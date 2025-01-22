package com.example.projektpo.oldentities;

import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer> {
    Client findById(int id);
}
