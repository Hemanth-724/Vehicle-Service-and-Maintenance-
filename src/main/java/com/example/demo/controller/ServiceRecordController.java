package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.ServiceCompleteRequestDTO;
import com.example.demo.dto.response.ServiceRecordResponseDTO;
import com.example.demo.service.ServiceRecordService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/services")
public class ServiceRecordController {

    private final ServiceRecordService recordService;

    @PostMapping("/complete")
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceRecordResponseDTO completeService(@Valid @RequestBody ServiceCompleteRequestDTO dto) {
        return recordService.completeService(dto);
    }

    @GetMapping
    public List<ServiceRecordResponseDTO> getAllServices() {
        return recordService.getAllServiceRecords();
    }
}
