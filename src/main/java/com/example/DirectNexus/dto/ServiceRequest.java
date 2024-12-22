package com.example.DirectNexus.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequest {
    private String customerFirstName;
    private String customerLastName;
    private String phoneNumber;
    private String emailAddress;
    private String address;
    private String productName;
    private String faultDescription;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate faultDate;
    private String servicePersonnel;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate operationDate;

    private int warrantyStatus;
    private String cargoStatus;
    private int serviceCompletionStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deliveryDate;

    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String operationPerformed;
    private String invoiceUrl;



}