package com.example.mtb.mapper;

import com.example.mtb.dto.UserRegistrationDTO;
import com.example.mtb.entity.TheaterOwner;
import com.example.mtb.entity.User;
import com.example.mtb.entity.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;

//@Component
//public class UserDetailsMapper {
//    public UserRegistrationDTO userDetailsResponseMapper(UserDetails userDetails){
//        if(userDetails == null)
//            return null;
//        return new UserRegistrationDTO(
//                userDetails.getUsername(),
//                userDetails.getEmail(),
//                userDetails.getPhoneNumber(),
//                userDetails.getPassword(),
//                userDetails.getUserRole(),
//                userDetails.getDateOfBirth()
//        );
//    }
//            }
@Component
public class UserDetailsMapper {
        private UserDetailsMapper() {} // Utility class

    public static UserDetails mapFromDTO(UserRegistrationDTO dto) {
        UserDetails user = switch (dto.userRole()) {
            case USER -> new User();
            case THEATER_OWNER -> new TheaterOwner();
        };

        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(dto.password()); // Hash later
        user.setPhoneNumber(dto.phoneNumber());
        user.setUserRole(dto.userRole());
        user.setDateOfBirth(dto.dateOfBirth());

        long now = Instant.now().toEpochMilli();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        return user;
    }
}
