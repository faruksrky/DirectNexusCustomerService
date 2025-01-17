package com.example.DirectNexus.controller;

import com.example.DirectNexus.config.JwtProperties;
import com.example.DirectNexus.dto.ServiceRequest;
import com.example.DirectNexus.dto.StatusResponse;
import com.example.DirectNexus.entity.ServiceEntity;
import com.example.DirectNexus.exception.ResourceNotFoundException;
import com.example.DirectNexus.service.ServiceRequestService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

@RestController
@RequestMapping("/api/service-requests")
@RequiredArgsConstructor
public class ServiceRequestController {
    private final ServiceRequestService serviceRequestService;
    private final JwtProperties jwtProperties;

    @GetMapping
    public List<ServiceEntity> getAllServiceRequests() {
        return serviceRequestService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceEntity> getServiceRequestById(@PathVariable Long id) throws ResourceNotFoundException {
        ServiceEntity serviceEntity = serviceRequestService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID: " + id + " No'lu Servis Kaydı Bulunamadı"));
        return ResponseEntity.ok(serviceEntity);
    }

    @PostMapping
    public ResponseEntity<String> saveService(@RequestBody ServiceRequest serviceEntityRequestDTO,
                                              @AuthenticationPrincipal Jwt jwt) {
        // Token kontrolü
        if (jwt == null) {
            return ResponseEntity.status(403).body("Yetkiniz yok. Lütfen geçerli bir token sağlayın.");
        }

        String customClaim = jwt.getClaim("customClaim");
        if (customClaim == null || !customClaim.equals(jwtProperties.getCustomClaim())) {
            return ResponseEntity.status(403).body("Token yetkili değil.");
        }

        // Token geçerliyse işlem devam eder
        ServiceEntity savedEntity = serviceRequestService.save(serviceEntityRequestDTO);
        return ResponseEntity.ok("Başarılı bir şekilde kaydedildi. ID: " + savedEntity.getId());
    }


    @PutMapping("/{id}")
    public ResponseEntity<ServiceEntity> updateServiceRequest(@PathVariable Long id, @RequestBody ServiceEntity serviceEntityRequest) {
        try {
            ServiceEntity updatedServiceEntityRequest = serviceRequestService.update(id, serviceEntityRequest);
            return ResponseEntity.ok(updatedServiceEntityRequest);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Tek bir ID'yi silme
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceRequest(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
        // Token kontrolü
        if (jwt == null) {
            return ResponseEntity.status(403).build();
        }

        String customClaim = jwt.getClaim("customClaim");
        if (customClaim == null || !customClaim.equals(jwtProperties.getCustomClaim())) {
            return ResponseEntity.status(403).build();
        }

        // Token geçerliyse silme işlemi devam eder
        serviceRequestService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Birden fazla ID'yi silme
    @DeleteMapping
    public ResponseEntity<Void> deleteServiceRequests(@RequestBody List<Long> ids, @AuthenticationPrincipal Jwt jwt) {
        // Token kontrolü
        if (jwt == null) {
            return ResponseEntity.status(403).build();
        }

        String customClaim = jwt.getClaim("customClaim");
        if (customClaim == null || !customClaim.equals(jwtProperties.getCustomClaim())) {
            return ResponseEntity.status(403).build();
        }

        // Token geçerliyse silme işlemi devam eder
        serviceRequestService.deleteByIds(ids);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<StatusResponse> getStatusByCustomerId(@PathVariable Long id) throws ResourceNotFoundException {
        StatusResponse statusResponse = serviceRequestService.getStatusByCustomerId(id);
        return ResponseEntity.ok(statusResponse);
    }


}