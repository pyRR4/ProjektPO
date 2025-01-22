package com.example.projektpo.service.contract;

import com.example.projektpo.dto.WarningDTO;

public interface WarningServiceContract {

    WarningDTO createWarning(WarningDTO warningDTO);

    void deleteWarning(int id);

    WarningDTO updateWarning(WarningDTO warningDTO);
}