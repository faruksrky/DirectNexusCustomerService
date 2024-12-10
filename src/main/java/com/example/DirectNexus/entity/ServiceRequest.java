package com.example.DirectNexus.entity;


import com.example.DirectNexus.enums.ServiceCompletionStatus;
import com.example.DirectNexus.enums.WarrantyStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "service_request_form")
public class ServiceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Customer Information
    @Column(name = "customer_first_name", nullable = false)
    private String customerFirstName;

    @Column(name = "customer_last_name", nullable = false)
    private String customerLastName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "address")
    private String address;

    // Product Information
    @Column(name = "product_name", nullable = false)
    private String productName;

    // Fault Information
    @Column(name = "fault_description")
    private String faultDescription;

    @Column(name = "fault_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate faultDate;

    // Service Information
    @Column(name = "service_personnel")
    private String servicePersonnel;

    @Column(name = "operation_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate operationDate;

    // Additional Information
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "warranty_status")
    private WarrantyStatus warrantyStatus;

    @Column(name = "cargo_status")
    private String cargoStatus;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "service_completion_status")
    private ServiceCompletionStatus serviceCompletionStatus;

    @Column(name = "delivery_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deliveryDate;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column (name="operation_performed")
    private String operationPerformed;

    @Column(name = "invoice_url")
    private String invoiceUrl;

}
