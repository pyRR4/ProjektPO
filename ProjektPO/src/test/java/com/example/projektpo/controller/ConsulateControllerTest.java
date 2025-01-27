package com.example.projektpo.controller;

import com.example.projektpo.dto.ConsulateDTO;
import com.example.projektpo.service.contract.ConsulateServiceContract;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(ConsulateController.class)
class ConsulateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ConsulateServiceContract consulateService;

    @WithMockUser
    @Test
    void shouldReturnAllConsulates() throws Exception {
        List<ConsulateDTO> consulates = List.of(
                new ConsulateDTO(1, "USA", "United States of America"),
                new ConsulateDTO(2, "FRA", "France"),
                new ConsulateDTO(3, "DEU", "Germany")
        );
        Mockito.when(consulateService.getAllConsulates()).thenReturn(consulates);

        mockMvc.perform(get("/api/consulates"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].code").value("FRA"));
    }

    @WithMockUser
    @Test
    void shouldFilterConsulatesByCountry() throws Exception {
        List<ConsulateDTO> consulates = List.of(
                new ConsulateDTO(1, "USA", "United States of America"),
                new ConsulateDTO(2, "FRA", "France"),
                new ConsulateDTO(3, "DEU", "Germany")
        );
        Mockito.when(consulateService.getAllConsulatesByCountry("USA")).thenReturn(consulates);

        mockMvc.perform(get("/api/consulates/filter/USA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].code").value("USA"))
                .andExpect(jsonPath("$[0].countryName").value("United States of America"));
    }


    @WithMockUser
    @Test
    void shouldCreateConsulate() throws Exception {
        Mockito.when(consulateService.createConsulate(Mockito.any(ConsulateDTO.class)))
                .thenReturn(null);

        mockMvc.perform(
                    post("/api/consulates")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"id\":3, \"code\":\"DEU\", \"countryName\":\"Germany\"}")
                    .with(csrf())
                )
                .andExpect(status().isCreated());
    }

    @WithMockUser
    @Test
    void shouldReturnConsulateById() throws Exception {
        ConsulateDTO consulateDTO = new ConsulateDTO(1, "USA", "United States of America");
        Mockito.when(consulateService.getConsulateById(1)).thenReturn(consulateDTO);

        mockMvc.perform(get("/api/consulates/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.code").value("USA"))
                .andExpect(jsonPath("$.countryName").value("United States of America"));
    }

    @WithMockUser
    @Test
    void shouldUpdateConsulate() throws Exception {
        Mockito.when(consulateService.updateConsulate(Mockito.any(ConsulateDTO.class)))
                .thenReturn(null);

        mockMvc.perform(
                    put("/api/consulates/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"id\":1, \"code\":\"US\", \"countryName\":\"United States of America\"}")
                    .with(csrf())
                )
                .andExpect(status().isNoContent());
    }

    @WithMockUser
    @Test
    void shouldDeleteConsulate() throws Exception {
        Mockito.doNothing().when(consulateService).deleteConsulate(1);

        mockMvc.perform(
                    delete("/api/consulates/1")
                    .with(csrf())
                )
                .andExpect(status().isNoContent());
    }

}

