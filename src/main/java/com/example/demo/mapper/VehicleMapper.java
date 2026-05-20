package com.example.demo.mapper;


import com.example.demo.dto.request.VehicleRequestDTO;
import com.example.demo.dto.response.VehicleResponseDTO;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Vehicle;
import com.example.demo.entity.VehicleStatus;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public Vehicle toEntity(VehicleRequestDTO dto, Customer customer) {
        return Vehicle.builder()
                .vehicleNumber(dto.getVehicleNumber())
                .brand(dto.getBrand())
                .model(dto.getModel())
                .status(VehicleStatus.AVAILABLE)
                .customer(customer)
                .build();
    }

    public VehicleResponseDTO toResponseDTO(Vehicle vehicle) {
        return VehicleResponseDTO.builder()
                .vehicleId(vehicle.getVehicleId())
                .vehicleNumber(vehicle.getVehicleNumber())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .status(vehicle.getStatus())
                .customerId(vehicle.getCustomer().getCustomerId())
                .customerName(vehicle.getCustomer().getName())
                .build();
    }
}