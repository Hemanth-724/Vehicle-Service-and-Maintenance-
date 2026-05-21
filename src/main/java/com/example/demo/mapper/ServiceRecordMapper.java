package com.example.demo.mapper;

import com.example.demo.dto.request.ServiceCompleteRequestDTO;
import com.example.demo.dto.response.ServiceRecordResponseDTO;
import com.example.demo.entity.ServiceRecord;
import com.example.demo.entity.ServiceBooking;
import org.springframework.stereotype.Component;

@Component
public class ServiceRecordMapper {

    public ServiceRecord toEntity(ServiceCompleteRequestDTO dto, ServiceBooking booking) {
        return ServiceRecord.builder()
                .serviceDate(dto.getServiceDate())
                .completionDate(dto.getCompletionDate())
                .remarks(dto.getRemarks())
                .serviceBooking(booking)
                .build();
    }

    public ServiceRecordResponseDTO toResponseDTO(ServiceRecord record) {
        return ServiceRecordResponseDTO.builder()
                .serviceRecordId(record.getServiceRecordId())
                .serviceDate(record.getServiceDate())
                .completionDate(record.getCompletionDate())
                .remarks(record.getRemarks())
                .bookingId(record.getServiceBooking().getBookingId())
                .build();
    }
}
