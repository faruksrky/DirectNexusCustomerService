package com.example.DirectNexus.controller;

import com.example.DirectNexus.entity.ServiceRequest;
import com.example.DirectNexus.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/service-requests")
public class ServiceRequestController {
    private final ServiceRequestService serviceRequestService;

    @Autowired
    public ServiceRequestController(ServiceRequestService serviceRequestService) {
        this.serviceRequestService = serviceRequestService;
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3031")
    public List<ServiceRequest> getAllServiceRequests() {
        return serviceRequestService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ServiceRequest> getServiceRequestById(@PathVariable UUID id) {
        return serviceRequestService.findById(id);
    }

    @PostMapping
    public ServiceRequest createServiceRequest(@RequestBody ServiceRequest serviceRequest) {
        return serviceRequestService.save(serviceRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteServiceRequest(@PathVariable UUID id) {
        serviceRequestService.deleteById(id);
    }
}
