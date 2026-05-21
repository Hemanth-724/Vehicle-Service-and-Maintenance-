package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.CustomerRequestDTO;
import com.example.demo.dto.response.CustomerResponseDTO;
import com.example.demo.dto.response.VehicleResponseDTO;
import com.example.demo.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseDTO registerCustomer(@Valid @RequestBody CustomerRequestDTO requestDTO) {
        return customerService.registerCustomer(requestDTO);
    }

    @GetMapping("/{id}")
    public CustomerResponseDTO getCustomerById(@PathVariable("id") Long customerId) {
        return customerService.getCustomerById(customerId);
    }

    @GetMapping("/{id}/vehicles")
    public List<VehicleResponseDTO> getCustomerVehicles(@PathVariable("id") Long customerId) {
        return customerService.getVehiclesByCustomerId(customerId);
    }
}
