package com.example.demo.service;


import com.example.demo.dto.request.VehicleRequestDTO;
import com.example.demo.dto.response.VehicleResponseDTO;

import java.util.List;

public interface VehicleService {

    VehicleResponseDTO addVehicle(VehicleRequestDTO requestDTO);

    List<VehicleResponseDTO> getAllVehicles();

    List<VehicleResponseDTO> getVehiclesInService();

    List<VehicleResponseDTO> searchVehicles(String vehicleNumber, String brand);
}