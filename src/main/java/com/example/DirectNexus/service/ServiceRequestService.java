package com.example.DirectNexus.service;

import com.example.DirectNexus.entity.ServiceRequest;
import com.example.DirectNexus.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceRequestService {
    private final ServiceRequestRepository serviceRequestRepository;

    @Autowired
    public ServiceRequestService(ServiceRequestRepository serviceRequestRepository) {
        this.serviceRequestRepository = serviceRequestRepository;
    }
    public ServiceRequest save(ServiceRequest serviceRequest) {
        return serviceRequestRepository.save(serviceRequest);
    }

    public List<ServiceRequest> findAll() {
        return serviceRequestRepository.findAll();
    }

    public Optional<ServiceRequest> findById(UUID id) {
        return serviceRequestRepository.findById(id);
    }

    public void deleteById(UUID id) {
        serviceRequestRepository.deleteById(id);
    }
}
