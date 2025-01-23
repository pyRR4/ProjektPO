package com.example.projektpo.service.contract;

import com.example.projektpo.dto.WarningDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WarningServiceContract {

    List<WarningDTO> getAllWarnings();

    @Transactional
    void createWarning(WarningDTO warningDTO);

    @Transactional
    void deleteWarning(int id);

    @Transactional
    void updateWarning(WarningDTO warningDTO);
}