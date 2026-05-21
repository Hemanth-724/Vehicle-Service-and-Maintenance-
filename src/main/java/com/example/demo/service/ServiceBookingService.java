package com.example.demo.service;

import com.example.demo.dto.request.BookingStatusUpdateDTO;
import com.example.demo.dto.request.ServiceBookingRequestDTO;
import com.example.demo.dto.response.ServiceBookingResponseDTO;

public interface ServiceBookingService {

    ServiceBookingResponseDTO createBooking(ServiceBookingRequestDTO requestDTO);

    ServiceBookingResponseDTO getBookingById(Long bookingId);

    ServiceBookingResponseDTO updateBookingStatus(Long bookingId, BookingStatusUpdateDTO dto);
}
