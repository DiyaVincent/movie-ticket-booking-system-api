package com.example.mtb.mapper;


import com.example.mtb.dto.UserResponse;
import com.example.mtb.entity.TheaterOwner;
import com.example.mtb.entity.User;
import com.example.mtb.entity.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UserDetailsMapper {
    public UserResponse userDetailsResponseMapper(UserDetails userDetails){
        if(userDetails == null)
            return null;
        return new UserResponse(
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getPhoneNumber(),

                userDetails.getUserRole(),
                userDetails.getDateOfBirth()
        );
    }
            }


//@Component
//public class UserDetailsMapper {
//        private UserDetailsMapper() {} // Utility class
//
//    public static UserDetails mapFromDTO(UserResponse dto) {
//        UserDetails user = switch (dto.userRole()) {
//            case USER -> new UserRegistrationRequest();
//            case THEATER_OWNER -> new TheaterOwner();
//        };
//
//        user.setUsername(dto.username());
//        user.setEmail(dto.email());
//        user.setPhoneNumber(dto.phoneNumber());
//        user.setUserRole(dto.userRole());
//        user.setDateOfBirth(dto.dateOfBirth());
//
//        long now = Instant.now().toEpochMilli();
//        user.setCreatedAt(Instant.ofEpochSecond(now));
//        user.setUpdatedAt(Instant.ofEpochSecond(now));
//
//        return user;
//    }
//}
