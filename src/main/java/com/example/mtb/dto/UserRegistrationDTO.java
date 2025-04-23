package com.example.mtb.dto;

import com.example.mtb.enums.UserRole;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserRegistrationDTO(

        String username,
        String email,
        String phoneNumber,
        String password,
        UserRole userRole,
        LocalDate dateOfBirth
) {

}
