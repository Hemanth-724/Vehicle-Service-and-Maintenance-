package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ServiceRecord;

public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Long> {

    boolean existsByServiceBookingBookingId(Long bookingId);

}
