package com.example.demo.dto.response;

import java.time.LocalDate;

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
public class ServiceRecordResponseDTO {

    private Long serviceRecordId;
    private LocalDate serviceDate;
    private LocalDate completionDate;
    private String remarks;
    private Long bookingId;
}
