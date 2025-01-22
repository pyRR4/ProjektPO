package com.example.projektpo.oldentities;

import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;

public interface MessageRepository extends CrudRepository<Message, Integer> {
    Message findById(int id);

    Message findAllByConsulateEmployeeId(int consulateEmployeeId);

    Message findAllByTimestamp(Timestamp timestamp);
}
