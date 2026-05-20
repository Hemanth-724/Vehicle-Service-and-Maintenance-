package com.example.demo.dto.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponseDTO {

    private Long customerId;
    private String name;
    private String phoneNumber;
    private String email;
}