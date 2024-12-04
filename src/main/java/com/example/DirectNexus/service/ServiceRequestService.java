package com.example.DirectNexus.service;

import com.example.DirectNexus.entity.ServiceRequest;
import com.example.DirectNexus.repository.ServiceRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceRequestService {
    private final ServiceRequestRepository serviceRequestRepository;

    public ServiceRequest save(ServiceRequest serviceRequest) {
        return serviceRequestRepository.save(serviceRequest);
    }
    public List<ServiceRequest> findAll() {
        return serviceRequestRepository.findAll();
    }
    public Optional<ServiceRequest> findById(Long id) {
        return serviceRequestRepository.findById(id);
    }
    public void deleteById(Long id) {
        serviceRequestRepository.deleteById(id);
    }
    public ServiceRequest update(Long id, ServiceRequest updatedServiceRequest) {
        return serviceRequestRepository.findById(id)
                .map(serviceRequest -> {
                    serviceRequest.setServicePersonnel(updatedServiceRequest.getServicePersonnel());
                    serviceRequest.setOperationDate(updatedServiceRequest.getOperationDate());
                    serviceRequest.setDeliveryDate(updatedServiceRequest.getDeliveryDate());
                    serviceRequest.setServiceCompletionStatus(updatedServiceRequest.getServiceCompletionStatus());
                    serviceRequest.setWarrantyStatus(updatedServiceRequest.getWarrantyStatus());
                    serviceRequest.setCargoStatus(updatedServiceRequest.getCargoStatus());
                    serviceRequest.setOperationPerformed(updatedServiceRequest.getOperationPerformed());
                    serviceRequest.setNotes(updatedServiceRequest.getNotes());
                    serviceRequest.setUpdatedAt(LocalDateTime.now());
                    return serviceRequestRepository.save(serviceRequest);
                })
                .orElseGet(() -> {
                    updatedServiceRequest.setId(id);
                    return serviceRequestRepository.save(updatedServiceRequest);
                });
    }
}