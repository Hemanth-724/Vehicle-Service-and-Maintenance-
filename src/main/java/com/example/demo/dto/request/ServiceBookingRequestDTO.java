package com.example.demo.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ServiceBookingRequestDTO {

    @NotNull(message = "Booking date is required")
    private LocalDateTime bookingDate;

    @NotBlank(message = "Service type is required")
    private String serviceType;

    @NotNull(message = "Vehicle ID is required")
    private Long vehicleId;
}
