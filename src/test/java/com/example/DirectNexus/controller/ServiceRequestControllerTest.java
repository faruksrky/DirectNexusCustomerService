package com.example.DirectNexus.controller;

import com.example.DirectNexus.entity.ServiceEntity;
import com.example.DirectNexus.service.ServiceRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ServiceRequestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ServiceRequestService serviceRequestService;

    @InjectMocks
    private ServiceRequestController serviceRequestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(serviceRequestController).build();
    }

    @Test
    void getAllServiceRequestsReturnsList() throws Exception {
        when(serviceRequestService.findAll()).thenReturn(Collections.singletonList(new ServiceEntity()));
        mockMvc.perform(get("/api/service-requests"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void getServiceRequestByIdReturnsServiceRequest() throws Exception {
        Long id = 1L;
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setId(id); // Ensure the id is set
        when(serviceRequestService.findById(id)).thenReturn(Optional.of(serviceEntity));
        mockMvc.perform(get("/api/service-requests/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void getServiceRequestByIdNotFound() throws Exception {
        Long id = 1L;
        when(serviceRequestService.findById(id)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/service-requests/{id}", id))
                .andExpect(status().isNotFound());
    }


    @Test
    void updateServiceRequestReturnsUpdated() throws Exception {
        Long id = 1L;
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setId(id); // Ensure the id is set
        when(serviceRequestService.update(any(Long.class), any(ServiceEntity.class))).thenReturn(serviceEntity);
        mockMvc.perform(put("/api/service-requests/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void updateServiceRequestNotFound() throws Exception {
        Long id = 1L;
        when(serviceRequestService.update(any(Long.class), any(ServiceEntity.class))).thenThrow(new RuntimeException("ServiceRequest not found with id " + id));
        mockMvc.perform(put("/api/service-requests/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteServiceRequestReturnsNoContent() throws Exception {
        Long id = 1L;
        mockMvc.perform(delete("/api/service-requests/{id}", id))
                .andExpect(status().isNoContent());
    }
}