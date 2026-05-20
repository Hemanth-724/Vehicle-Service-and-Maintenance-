package com.example.demo.service;

import com.example.demo.dto.request.CustomerRequestDTO;
import com.example.demo.dto.response.CustomerResponseDTO;
import com.example.demo.dto.response.VehicleResponseDTO;

import java.util.List;

public interface CustomerService {

    CustomerResponseDTO registerCustomer(CustomerRequestDTO requestDTO);

    CustomerResponseDTO getCustomerById(Long customerId);

    List<VehicleResponseDTO> getVehiclesByCustomerId(Long customerId);
}