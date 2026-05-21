package com.example.demo.dto.request;

import com.example.demo.enums.BookingStatus;

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
public class BookingStatusUpdateDTO {

    @NotNull(message = "Booking status is required")
    private BookingStatus bookingStatus;
}
