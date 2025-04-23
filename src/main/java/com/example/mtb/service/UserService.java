package com.example.mtb.service;

import com.example.mtb.dto.UserRegistrationDTO;
import com.example.mtb.entity.UserDetails;

public interface UserService {

//    UserRegistrationDTO addUser(UserRegistrationRequest user);

    UserDetails addUser(UserRegistrationDTO dto);
}
