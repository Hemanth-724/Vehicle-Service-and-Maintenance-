package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ServiceBooking;
import com.example.demo.enums.BookingStatus;

public interface ServiceBookingRepository extends JpaRepository<ServiceBooking, Long> {

    List<ServiceBooking> findByVehicleVehicleIdAndBookingStatusIn(Long vehicleId, List<BookingStatus> statuses);

}
