package com.example.DirectNexus.service;

import com.example.DirectNexus.dto.ServiceRequest;
import com.example.DirectNexus.dto.StatusResponse;
import com.example.DirectNexus.entity.ServiceEntity;
import com.example.DirectNexus.enums.ServiceCompletionStatus;
import com.example.DirectNexus.enums.WarrantyStatus;
import com.example.DirectNexus.exception.ResourceNotFoundException;
import com.example.DirectNexus.repository.ServiceRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceRequestService {
    private final ServiceRequestRepository serviceRequestRepository;

    public ServiceEntity save(ServiceRequest serviceEntityRequestDTO) {

        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setCustomerFirstName(serviceEntityRequestDTO.getCustomerFirstName());
        serviceEntity.setCustomerLastName(serviceEntityRequestDTO.getCustomerLastName());
        serviceEntity.setPhoneNumber(serviceEntityRequestDTO.getPhoneNumber());
        serviceEntity.setEmailAddress(serviceEntityRequestDTO.getEmailAddress());
        serviceEntity.setAddress(serviceEntityRequestDTO.getAddress());
        serviceEntity.setProductName(serviceEntityRequestDTO.getProductName());
        serviceEntity.setFaultDescription(serviceEntityRequestDTO.getFaultDescription());
        serviceEntity.setFaultDate(LocalDate.now());
        serviceEntity.setServicePersonnel(serviceEntityRequestDTO.getServicePersonnel());
        serviceEntity.setOperationDate(LocalDate.now());
        serviceEntity.setWarrantyStatus(WarrantyStatus.values()[serviceEntityRequestDTO.getWarrantyStatus()]);
        serviceEntity.setServiceCompletionStatus(ServiceCompletionStatus.Talebiniz_Alındı);
        serviceEntity.setNotes(serviceEntityRequestDTO.getNotes());
        serviceEntity.setOperationPerformed(serviceEntityRequestDTO.getOperationPerformed());
        serviceEntity.setInvoiceUrl(serviceEntityRequestDTO.getInvoiceUrl());

        return serviceRequestRepository.save(serviceEntity);
    }


    public List<ServiceEntity> findAll() {
        return serviceRequestRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Optional<ServiceEntity> findById(Long id) throws ResourceNotFoundException{
        return serviceRequestRepository.findById(id);
    }

    public void deleteById(Long id) {
        serviceRequestRepository.deleteById(id);
    }

    // Birden fazla ID'yi silme
    public void deleteByIds(List<Long> ids) {
        ids.forEach(this::deleteById);
    }

    public ServiceEntity update(Long id, ServiceEntity updatedServiceEntityRequest) {
        return serviceRequestRepository.findById(id)
                .map(serviceEntityRequest -> {
                    serviceEntityRequest.setServicePersonnel(updatedServiceEntityRequest.getServicePersonnel());
                    serviceEntityRequest.setEmailAddress(updatedServiceEntityRequest.getEmailAddress());
                    serviceEntityRequest.setPhoneNumber(updatedServiceEntityRequest.getPhoneNumber());
                    serviceEntityRequest.setProductName(updatedServiceEntityRequest.getProductName());
                    serviceEntityRequest.setFaultDescription(updatedServiceEntityRequest.getFaultDescription());
                    serviceEntityRequest.setOperationDate(updatedServiceEntityRequest.getOperationDate());
                    serviceEntityRequest.setDeliveryDate(updatedServiceEntityRequest.getDeliveryDate());
                    serviceEntityRequest.setServiceCompletionStatus(updatedServiceEntityRequest.getServiceCompletionStatus());
                    serviceEntityRequest.setWarrantyStatus(updatedServiceEntityRequest.getWarrantyStatus());
                    serviceEntityRequest.setCargoStatus(updatedServiceEntityRequest.getCargoStatus());
                    serviceEntityRequest.setOperationPerformed(updatedServiceEntityRequest.getOperationPerformed());
                    serviceEntityRequest.setNotes(updatedServiceEntityRequest.getNotes());
                    serviceEntityRequest.setUpdatedAt(LocalDateTime.now());
                    return serviceRequestRepository.save(serviceEntityRequest);
                })
                .orElseGet(() -> {
                    updatedServiceEntityRequest.setId(id);
                    return serviceRequestRepository.save(updatedServiceEntityRequest);
                });
    }

    public StatusResponse getStatusByCustomerId(Long customerId) {
        ServiceEntity serviceEntity = findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("ID: " + customerId + " No'lu Servis Kaydı Bulunamadı"));


        return new StatusResponse(
                serviceEntity.getServiceCompletionStatus().name(),
                serviceEntity.getCargoStatus()
        );
    }
}