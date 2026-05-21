package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.request.ServiceCompleteRequestDTO;
import com.example.demo.dto.response.ServiceRecordResponseDTO;

public interface ServiceRecordService {

    ServiceRecordResponseDTO completeService(ServiceCompleteRequestDTO dto);

    List<ServiceRecordResponseDTO> getAllServiceRecords();
}
