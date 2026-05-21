package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.demo.dto.request.VehicleRequestDTO;
import com.example.demo.dto.response.VehicleResponseDTO;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Vehicle;
import com.example.demo.enums.VehicleStatus;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.exception.InvalidVehicleSearchException;
import com.example.demo.exception.VehicleAlreadyExistsException;
import com.example.demo.mapper.VehicleMapper;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.VehicleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final CustomerRepository customerRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    @Transactional
    public VehicleResponseDTO addVehicle(VehicleRequestDTO requestDTO) {

        if (vehicleRepository.existsByVehicleNumberIgnoreCase(requestDTO.getVehicleNumber())) {
            throw new VehicleAlreadyExistsException(
                    "Vehicle already exists with number: " + requestDTO.getVehicleNumber());
        }

        Customer customer = customerRepository.findById(requestDTO.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Customer not found with ID: " + requestDTO.getCustomerId()));

        Vehicle vehicle = vehicleMapper.toEntity(requestDTO, customer);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return vehicleMapper.toResponseDTO(savedVehicle);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleResponseDTO> getAllVehicles() {

        return vehicleRepository.findAll()
                .stream()
                .map(vehicleMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleResponseDTO> getVehiclesInService() {

        return vehicleRepository.findByStatus(VehicleStatus.IN_SERVICE)
                .stream()
                .map(vehicleMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleResponseDTO> searchVehicles(String vehicleNumber, String brand) {

        boolean hasVehicleNumber = StringUtils.hasText(vehicleNumber);
        boolean hasBrand = StringUtils.hasText(brand);

        if (!hasVehicleNumber && !hasBrand) {
            throw new InvalidVehicleSearchException(
                    "Provide at least vehicleNumber or brand for searching");
        }

        List<Vehicle> vehicles;

        if (hasVehicleNumber && hasBrand) {
            vehicles = vehicleRepository
                    .findByVehicleNumberContainingIgnoreCaseOrBrandContainingIgnoreCase(
                            vehicleNumber.trim(),
                            brand.trim());
        } else if (hasVehicleNumber) {
            vehicles = vehicleRepository
                    .findByVehicleNumberContainingIgnoreCase(vehicleNumber.trim());
        } else {
            vehicles = vehicleRepository
                    .findByBrandContainingIgnoreCase(brand.trim());
        }

        return vehicles.stream()
                .map(vehicleMapper::toResponseDTO)
                .toList();
    }
}