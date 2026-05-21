package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.BookingStatusUpdateDTO;
import com.example.demo.dto.request.ServiceBookingRequestDTO;
import com.example.demo.dto.response.ServiceBookingResponseDTO;
import com.example.demo.service.ServiceBookingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class ServiceBookingController {

    private final ServiceBookingService bookingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceBookingResponseDTO createBooking(@Valid @RequestBody ServiceBookingRequestDTO dto) {
        return bookingService.createBooking(dto);
    }

    @GetMapping("/{id}")
    public ServiceBookingResponseDTO getBooking(@PathVariable("id") Long id) {
        return bookingService.getBookingById(id);
    }

    @PutMapping("/status/{bookingId}")
    public ServiceBookingResponseDTO updateStatus(@PathVariable("bookingId") Long bookingId,
                                                  @Valid @RequestBody BookingStatusUpdateDTO dto) {
        return bookingService.updateBookingStatus(bookingId, dto);
    }
}
