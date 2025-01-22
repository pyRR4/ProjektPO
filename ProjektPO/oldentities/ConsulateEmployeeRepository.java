package com.example.projektpo.oldentities;

import org.springframework.data.repository.CrudRepository;

public interface ConsulateEmployeeRepository extends CrudRepository<ConsulateEmployee, Integer> {
    ConsulateEmployee findById(int id);

    ConsulateEmployee findAllByConsulateId(int consulateId);
}
