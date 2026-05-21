package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.request.BookingStatusUpdateDTO;
import com.example.demo.dto.request.ServiceBookingRequestDTO;
import com.example.demo.dto.response.ServiceBookingResponseDTO;
import com.example.demo.entity.ServiceBooking;
import com.example.demo.entity.Vehicle;
import com.example.demo.enums.BookingStatus;
import com.example.demo.exception.ActiveBookingExistsException;
import com.example.demo.exception.BookingNotFoundException;
import com.example.demo.exception.VehicleNotFoundException;
import com.example.demo.mapper.ServiceBookingMapper;
import com.example.demo.repository.ServiceBookingRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.ServiceBookingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiceBookingServiceImpl implements ServiceBookingService {

    private final ServiceBookingRepository bookingRepository;
    private final VehicleRepository vehicleRepository;
    private final ServiceBookingMapper bookingMapper;

    private static final List<BookingStatus> ACTIVE_STATUSES = List.of(
            BookingStatus.PENDING, BookingStatus.CONFIRMED, BookingStatus.IN_PROGRESS
    );

    @Override
    @Transactional
    public ServiceBookingResponseDTO createBooking(ServiceBookingRequestDTO requestDTO) {
        Vehicle vehicle = vehicleRepository.findById(requestDTO.getVehicleId())
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with ID: " + requestDTO.getVehicleId()));

        // check active booking for vehicle
        List<ServiceBooking> active = bookingRepository.findByVehicleVehicleIdAndBookingStatusIn(vehicle.getVehicleId(), ACTIVE_STATUSES);
        if (!active.isEmpty()) {
            throw new ActiveBookingExistsException("Vehicle already has an active booking");
        }

        ServiceBooking booking = bookingMapper.toEntity(requestDTO, vehicle);
        ServiceBooking saved = bookingRepository.save(booking);

        // set vehicle status to IN_SERVICE
        vehicle.setStatus(com.example.demo.enums.VehicleStatus.IN_SERVICE);
        vehicleRepository.save(vehicle);

        return bookingMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceBookingResponseDTO getBookingById(Long bookingId) {
        ServiceBooking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + bookingId));

        return bookingMapper.toResponseDTO(booking);
    }

    @Override
    @Transactional
    public ServiceBookingResponseDTO updateBookingStatus(Long bookingId, BookingStatusUpdateDTO dto) {
        ServiceBooking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + bookingId));

        booking.setBookingStatus(dto.getBookingStatus());
        ServiceBooking saved = bookingRepository.save(booking);

        return bookingMapper.toResponseDTO(saved);
    }
}
