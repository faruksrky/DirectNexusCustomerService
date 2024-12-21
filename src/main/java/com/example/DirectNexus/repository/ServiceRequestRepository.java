package com.example.DirectNexus.repository;

import com.example.DirectNexus.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ServiceRequestRepository extends JpaRepository<ServiceEntity, Long> {
}
