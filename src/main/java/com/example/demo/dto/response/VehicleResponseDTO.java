package com.example.demo.dto.response;

import com.example.demo.entity.VehicleStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleResponseDTO {

    private Long vehicleId;
    private String vehicleNumber;
    private String brand;
    private String model;
    private VehicleStatus status;

    private Long customerId;
    private String customerName;
}