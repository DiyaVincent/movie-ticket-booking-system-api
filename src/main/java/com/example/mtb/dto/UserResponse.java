package com.example.mtb.dto;

import com.example.mtb.enums.UserRole;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserResponse(
        String username,
        String email,
        String phoneNumber,
        UserRole userRole,
        LocalDate dateOfBirth
) {
}
