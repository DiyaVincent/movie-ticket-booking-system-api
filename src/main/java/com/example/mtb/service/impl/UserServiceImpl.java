package com.example.mtb.service.impl;

import com.example.mtb.dto.UserRegistrationRequest;
import com.example.mtb.dto.UserResponse;
import com.example.mtb.dto.UserUpdationRequest;
import com.example.mtb.entity.TheaterOwner;
import com.example.mtb.entity.User;
import com.example.mtb.entity.UserDetails;
import com.example.mtb.exceptions.UserExistByEmailException;
import com.example.mtb.exceptions.UserNotFoundByEmailException;
import com.example.mtb.mapper.UserDetailsMapper;
import com.example.mtb.repository.UserRepository;
import com.example.mtb.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static com.example.mtb.enums.UserRole.THEATER_OWNER;
import static com.example.mtb.enums.UserRole.USER;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDetailsMapper userMapper;

    @Override
    public UserResponse addUser(UserRegistrationRequest user) {
        if (userRepository.existsByEmail(user.email()))
            throw new UserExistByEmailException("User with the Email is already exists");

        UserDetails userDetails = switch (user.userRole()) {
            case USER -> copy(new User(), user);
            case THEATER_OWNER -> copy(new TheaterOwner(), user);
        };
        return userMapper.userDetailsResponseMapper(userDetails);

    }

    @Override
    public UserResponse editUser(UserUpdationRequest userRequest, String email) {
        if (userRepository.existsByEmail(email)){
            UserDetails user = userRepository.findByEmail(email);

//            if( userRepository.existsByEmail(userRequest.email()))
//                throw new UserExistByEmailException("User with the email already exists");

            user = copy(user, userRequest);

            return userMapper.userDetailsResponseMapper(user);
        }

        throw new UserNotFoundByEmailException("Email not found in the Database");

    }

    private UserDetails copy(UserDetails userRole, UserRegistrationRequest user) {
        userRole.setUserRole(user.userRole());
        userRole.setPassword(user.password());
        userRole.setEmail(user.email());
        userRole.setDateOfBirth(user.dateOfBirth());
        userRole.setPhoneNumber(user.phoneNumber());
        userRole.setUsername(user.username());
        userRepository.save(userRole);
        return userRole;
    }

    private UserDetails copy(UserDetails userRole, UserUpdationRequest user) {
//        userRole.setUsername(user.username());
////        userRole.setEmail(user.email());
//        userRole.setPhoneNumber(user.phoneNumber());
//        userRole.setDateOfBirth(user.dateOfBirth());
//        userRepository.save(userRole);
//        return userRole;
        userRole.setDateOfBirth(user.dateOfBirth());
        userRole.setPhoneNumber(user.phoneNumber());
        userRole.setEmail(user.email());
        userRole.setUsername(user.username());
        userRole.setDelete(false);
        userRepository.save(userRole);
        return userRole;
    }

    @Override
    public UserResponse softDeleteUser(String email) {
        if (userRepository.existsByEmail(email)) {
            UserDetails user = userRepository.findByEmail(email);
            user.setDelete(true);
            user.setDeletedAt(Instant.now());
            userRepository.save(user);
            return userMapper.userDetailsResponseMapper(user);
        }
        throw new UserNotFoundByEmailException("Email not found in the Database");
    }
}


