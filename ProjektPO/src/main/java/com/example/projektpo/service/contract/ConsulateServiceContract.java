package com.example.projektpo.service.contract;

import com.example.projektpo.dto.ConsulateDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ConsulateServiceContract {

    List<ConsulateDTO> getAllConsulates();

    List<ConsulateDTO> getAllConsulatesByCountry(String name);

    ConsulateDTO getConsulateById(int id);

    ConsulateDTO createConsulate(ConsulateDTO consulateDTO);

    ConsulateDTO updateConsulate(ConsulateDTO consulateDTO);

    @Transactional
    void deleteConsulate(int id);
}
