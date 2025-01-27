package com.example.projektpo.service.implementation;

import com.example.projektpo.dto.WarningDTO;
import com.example.projektpo.mappers.WarningMapper;
import com.example.projektpo.service.contract.ExternalSystemServiceContract;
import com.example.projektpo.service.contract.ParameterServiceContract;
import com.example.projektpo.service.contract.WarningServiceContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobScheduler {

    private final ExternalSystemServiceContract externalSystemService;
    private final WarningServiceContract warningService;
    private final ParameterServiceContract parameterService;
    private final WarningMapper warningMapper;

    private final Logger logger = LoggerFactory.getLogger(JobScheduler.class);

    private String parameterName = "Warning download frequency";
    private int downloadFrequency;

    @Autowired
    public JobScheduler(
            ExternalSystemServiceContract externalSystemService,
            WarningServiceContract warningService,
            ParameterServiceContract parameterService,
            WarningMapper warningMapper
    ) {
        this.externalSystemService = externalSystemService;
        this.warningService = warningService;
        this.parameterService = parameterService;
        this.warningMapper = warningMapper;
        this.downloadFrequency = 0;
    }

    public void downloadWarnings() {
        String externalWarningsString = externalSystemService.fetchWarnings();
        updateDownloadedWarnings(externalWarningsString);
    }

    public void setDownloadFrequency() {
        int frequencyInDays = parameterService.getParameterValueByName(parameterName);

        downloadFrequency = frequencyInDays * 60 * 1000;
        logger.debug(downloadFrequency + "");
    }

    public int getFrequency() {
        if (downloadFrequency <= 0) {
            setDownloadFrequency();
        }
        return (downloadFrequency > 0) ? downloadFrequency : 60000;
    }


    @Transactional
    public void updateDownloadedWarnings(String downloadedWarnings) {
        List<WarningDTO> externalWarnings = warningMapper.fromJson(downloadedWarnings);
        List<WarningDTO> currentWarnings = warningService.getAllWarnings();

        logger.debug(currentWarnings.toString());

        List<WarningDTO> removedWarnings = new ArrayList<>(currentWarnings);

        for (WarningDTO externalWarning : externalWarnings) {
            Optional<WarningDTO> existingWarningOpt = currentWarnings.stream()
                    .filter(w -> w.countryCode().equals(externalWarning.countryCode()))
                    .findFirst();

            if (existingWarningOpt.isPresent()) {
                WarningDTO existingWarning = existingWarningOpt.get();
                if (!existingWarning.equals(externalWarning)) {
                    warningService.updateWarning(externalWarning);
                }
                removedWarnings.remove(existingWarning);
            } else {
                warningService.createWarning(externalWarning);
            }
        }

        removedWarnings.forEach(warning -> warningService.deleteWarning(warning.id()));
    }
}
