package com.example.DirectNexus.repository;

import com.example.DirectNexus.entity.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {

}
