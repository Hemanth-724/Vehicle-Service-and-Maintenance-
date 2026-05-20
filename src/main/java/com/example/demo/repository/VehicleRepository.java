package com.example.demo.repository;

import com.example.demo.entity.Vehicle;
import com.example.demo.entity.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    boolean existsByVehicleNumberIgnoreCase(String vehicleNumber);

    List<Vehicle> findByStatus(VehicleStatus status);

    List<Vehicle> findByCustomerCustomerId(Long customerId);

    List<Vehicle> findByVehicleNumberContainingIgnoreCase(String vehicleNumber);

    List<Vehicle> findByBrandContainingIgnoreCase(String brand);

    List<Vehicle> findByVehicleNumberContainingIgnoreCaseOrBrandContainingIgnoreCase(
            String vehicleNumber,
            String brand
    );
}