package com.example.demo.service.impl;



import com.example.demo.dto.request.CustomerRequestDTO;
import com.example.demo.dto.response.CustomerResponseDTO;
import com.example.demo.dto.response.VehicleResponseDTO;
import com.example.demo.entity.Customer;
import com.example.demo.exception.CustomerAlreadyExistsException;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.mapper.VehicleMapper;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;
    private final CustomerMapper customerMapper;
    private final VehicleMapper vehicleMapper;

    @Override
    @Transactional
    public CustomerResponseDTO registerCustomer(CustomerRequestDTO requestDTO) {

        if (customerRepository.existsByEmailIgnoreCase(requestDTO.getEmail())) {
            throw new CustomerAlreadyExistsException("Customer already exists with email: " + requestDTO.getEmail());
        }

        if (customerRepository.existsByPhoneNumber(requestDTO.getPhoneNumber())) {
            throw new CustomerAlreadyExistsException("Customer already exists with phone number: " + requestDTO.getPhoneNumber());
        }

        Customer customer = customerMapper.toEntity(requestDTO);
        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.toResponseDTO(savedCustomer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponseDTO getCustomerById(Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + customerId));

        return customerMapper.toResponseDTO(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleResponseDTO> getVehiclesByCustomerId(Long customerId) {

        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
        }

        return vehicleRepository.findByCustomerCustomerId(customerId)
                .stream()
                .map(vehicleMapper::toResponseDTO)
                .toList();
    }
}