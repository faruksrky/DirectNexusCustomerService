package com.example.DirectNexus.controller;

import com.example.DirectNexus.entity.ServiceRequest;
import com.example.DirectNexus.exception.ResourceNotFoundException;
import com.example.DirectNexus.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-requests")
public class ServiceRequestController {
    private final ServiceRequestService serviceRequestService;

    @Autowired
    public ServiceRequestController(ServiceRequestService serviceRequestService) {
        this.serviceRequestService = serviceRequestService;
    }

    @GetMapping
    public List<ServiceRequest> getAllServiceRequests() {
        return serviceRequestService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceRequest> getServiceRequestById(@PathVariable Long id) throws ResourceNotFoundException{
        return serviceRequestService.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
}

    @GetMapping("/completionStatus/{id}")
    public String getCompletionStatusById(@PathVariable Long id)  throws ResourceNotFoundException {
        return serviceRequestService.findByCompletionStatusById(id);
    }

    @PostMapping
    public ServiceRequest createServiceRequest(@RequestBody ServiceRequest serviceRequest) {
        return serviceRequestService.save(serviceRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceRequest> updateServiceRequest(@PathVariable Long id, @RequestBody ServiceRequest serviceRequest) {
        try {
            ServiceRequest updatedServiceRequest = serviceRequestService.update(id, serviceRequest);
            return ResponseEntity.ok(updatedServiceRequest);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceRequest(@PathVariable Long id) {
        serviceRequestService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
