package com.example.projektpo.service;

import com.example.projektpo.dto.WarningDTO;
import com.example.projektpo.mappers.WarningMapper;
import com.example.projektpo.service.contract.ExternalSystemServiceContract;
import com.example.projektpo.service.contract.ParameterServiceContract;
import com.example.projektpo.service.contract.WarningServiceContract;
import com.example.projektpo.service.implementation.JobScheduler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class JobSchedulerTest {

    @Mock
    private ExternalSystemServiceContract externalSystemService;

    @Mock
    private WarningServiceContract warningService;

    @Mock
    private ParameterServiceContract parameterService;

    @Mock
    private WarningMapper warningMapper;

    @InjectMocks
    private JobScheduler jobScheduler;

    @Test
    void shouldCallFetchWarningsAndUpdateWarnings() {
        String fetchedWarnings = "[{\"id\":1,\"countryCode\":\"USA\",\"message\":\"Test\"}]";
        Mockito.when(externalSystemService.fetchWarnings()).thenReturn(fetchedWarnings);

        jobScheduler.downloadWarnings();

        Mockito.verify(externalSystemService).fetchWarnings();
        Mockito.verify(warningMapper).fromJson(fetchedWarnings);
    }

    @Test
    void shouldSetDownloadFrequency() {
        String parameterName = "Warning download frequency";
        int frequencyInDays = 2;
        Mockito.when(parameterService.getParameterValueByName(parameterName)).thenReturn(frequencyInDays);

        jobScheduler.setDownloadFrequency();

        int expectedFrequency = frequencyInDays * 60 * 1000;
        Mockito.verify(parameterService).getParameterValueByName(parameterName);
        assertEquals(expectedFrequency, jobScheduler.getFrequency());
    }

    @Test
    void shouldCreateNewWarnings() {
        String downloadedWarnings = "[{\"id\":1,\"countryCode\":\"USA\",\"message\":\"New Warning\"}]";
        List<WarningDTO> externalWarnings = List.of(new WarningDTO(1, "USA", "New Warning"));
        List<WarningDTO> currentWarnings = new ArrayList<>();

        Mockito.when(warningMapper.fromJson(downloadedWarnings)).thenReturn(externalWarnings);
        Mockito.when(warningService.getAllWarnings()).thenReturn(currentWarnings);

        jobScheduler.updateDownloadedWarnings(downloadedWarnings);

        Mockito.verify(warningService).createWarning(externalWarnings.get(0));
        Mockito.verify(warningService, Mockito.never()).deleteWarning(Mockito.anyInt());
        Mockito.verify(warningService, Mockito.never()).updateWarning(Mockito.any());
    }

    @Test
    void shouldDeleteObsoleteWarnings() {
        String downloadedWarnings = "[]";
        List<WarningDTO> currentWarnings = List.of(new WarningDTO(1, "US", "Old Warning"));

        Mockito.when(warningMapper.fromJson(downloadedWarnings)).thenReturn(new ArrayList<>());
        Mockito.when(warningService.getAllWarnings()).thenReturn(currentWarnings);

        jobScheduler.updateDownloadedWarnings(downloadedWarnings);

        Mockito.verify(warningService).deleteWarning(1);
        Mockito.verify(warningService, Mockito.never()).createWarning(Mockito.any());
        Mockito.verify(warningService, Mockito.never()).updateWarning(Mockito.any());
    }

    @Test
    void shouldReturnDefaultFrequencyWhenNotSet() {
        int frequency = jobScheduler.getFrequency();

        assertEquals(60000, frequency);
    }
}

