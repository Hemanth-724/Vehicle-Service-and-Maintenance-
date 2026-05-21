package com.example.demo.dto.response;

import java.time.LocalDateTime;

import com.example.demo.enums.BookingStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceBookingResponseDTO {

    private Long bookingId;
    private LocalDateTime bookingDate;
    private String serviceType;
    private BookingStatus bookingStatus;
    private Long vehicleId;
}
