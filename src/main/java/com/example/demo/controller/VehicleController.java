package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.VehicleRequestDTO;
import com.example.demo.dto.response.VehicleResponseDTO;
import com.example.demo.service.VehicleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleResponseDTO addVehicle(@Valid @RequestBody VehicleRequestDTO requestDTO) {
        return vehicleService.addVehicle(requestDTO);
    }

    @GetMapping
    public List<VehicleResponseDTO> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/in-service")
    public List<VehicleResponseDTO> getVehiclesInService() {
        return vehicleService.getVehiclesInService();
    }

    @GetMapping("/search")
    public List<VehicleResponseDTO> searchVehicles(
            @RequestParam(required = false) String vehicleNumber,
            @RequestParam(required = false) String brand) {
        return vehicleService.searchVehicles(vehicleNumber, brand);
    }
}
