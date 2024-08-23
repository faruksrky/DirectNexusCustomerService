package com.example.DirectNexus.entity;


import com.example.DirectNexus.enums.PaymentStatus;
import com.example.DirectNexus.enums.ServiceCompletionStatus;
import com.example.DirectNexus.enums.WarrantyStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "service_request_form")
public class ServiceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

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

    @Column(name = "model")
    private String model;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    // Fault Information
    @Column(name = "fault_description")
    private String faultDescription;

    @Column(name = "fault_date")
    private LocalDate faultDate;

    @Column(name = "preliminary_diagnosis")
    private String preliminaryDiagnosis;

    // Service Information
    @Column(name = "service_personnel")
    private String servicePersonnel;

    @Column(name = "operation_date")
    private LocalDate operationDate;

    @Column(name = "performed_operations")
    private String performedOperations;

    @Column(name = "replaced_parts")
    private String replacedParts;

    // Additional Information
    @Enumerated(EnumType.STRING)
    @Column(name = "warranty_status")
    private WarrantyStatus warrantyStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_completion_status")
    private ServiceCompletionStatus serviceCompletionStatus;


    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    // Metadata
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
