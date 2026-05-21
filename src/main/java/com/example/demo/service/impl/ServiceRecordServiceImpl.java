package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.request.ServiceCompleteRequestDTO;
import com.example.demo.dto.response.ServiceRecordResponseDTO;
import com.example.demo.entity.ServiceBooking;
import com.example.demo.entity.ServiceRecord;
import com.example.demo.enums.BookingStatus;
import com.example.demo.exception.BookingNotFoundException;
import com.example.demo.exception.ServiceRecordAlreadyExistsException;
import com.example.demo.mapper.ServiceRecordMapper;
import com.example.demo.repository.ServiceBookingRepository;
import com.example.demo.repository.ServiceRecordRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.ServiceRecordService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiceRecordServiceImpl implements ServiceRecordService {

    private final ServiceRecordRepository recordRepository;
    private final ServiceBookingRepository bookingRepository;
    private final VehicleRepository vehicleRepository;
    private final ServiceRecordMapper recordMapper;

    @Override
    @Transactional
    public ServiceRecordResponseDTO completeService(ServiceCompleteRequestDTO dto) {
        ServiceBooking booking = bookingRepository.findById(dto.getBookingId())
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + dto.getBookingId()));

        // booking must be active
        if (booking.getBookingStatus() == BookingStatus.COMPLETED) {
            throw new ServiceRecordAlreadyExistsException("Service already completed for booking: " + dto.getBookingId());
        }

        if (recordRepository.existsByServiceBookingBookingId(dto.getBookingId())) {
            throw new ServiceRecordAlreadyExistsException("Service record already exists for booking: " + dto.getBookingId());
        }

        ServiceRecord record = recordMapper.toEntity(dto, booking);
        ServiceRecord saved = recordRepository.save(record);

        // update booking status to COMPLETED
        booking.setBookingStatus(BookingStatus.COMPLETED);
        bookingRepository.save(booking);

        // update vehicle status to AVAILABLE
        var vehicle = booking.getVehicle();
        vehicle.setStatus(com.example.demo.enums.VehicleStatus.AVAILABLE);
        vehicleRepository.save(vehicle);

        return recordMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceRecordResponseDTO> getAllServiceRecords() {
        return recordRepository.findAll().stream()
                .map(recordMapper::toResponseDTO)
                .toList();
    }
}
