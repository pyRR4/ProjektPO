package com.example.projektpo;

import org.springframework.data.repository.CrudRepository;

public interface ConsulateEmployeeRepository extends CrudRepository<ConsulateEmployee, Integer> {
    ConsulateEmployee findById(int id);

    ConsulateEmployee findAllByConsulateId(int consulateId);
}
