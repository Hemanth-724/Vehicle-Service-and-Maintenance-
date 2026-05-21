package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.request.ServiceBookingRequestDTO;
import com.example.demo.dto.response.ServiceBookingResponseDTO;
import com.example.demo.entity.ServiceBooking;
import com.example.demo.entity.Vehicle;

@Component
public class ServiceBookingMapper {

    public ServiceBooking toEntity(ServiceBookingRequestDTO dto, Vehicle vehicle) {
        return ServiceBooking.builder()
                .bookingDate(dto.getBookingDate())
                .serviceType(dto.getServiceType())
                .bookingStatus(com.example.demo.enums.BookingStatus.PENDING)
                .vehicle(vehicle)
                .build();
    }

    public ServiceBookingResponseDTO toResponseDTO(ServiceBooking booking) {
        return ServiceBookingResponseDTO.builder()
                .bookingId(booking.getBookingId())
                .bookingDate(booking.getBookingDate())
                .serviceType(booking.getServiceType())
                .bookingStatus(booking.getBookingStatus())
                .vehicleId(booking.getVehicle().getVehicleId())
                .build();
    }
}
