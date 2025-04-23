package com.example.mtb.controller;

import com.example.mtb.dto.UserRegistrationDTO;
import com.example.mtb.dto.UserResponse;
import com.example.mtb.entity.UserDetails;
import com.example.mtb.service.UserService;
import com.example.mtb.util.ResponseStructure;
import com.example.mtb.util.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final RestResponseBuilder responseBuilder;

//    @PostMapping("/register")
//    public ResponseEntity<ResponseStructure<UserRegistrationDTO>> addUser(@RequestBody UserRegistrationRequest user){
//        UserRegistrationDTO userDetails = userService.addUser(user);
//        return responseBuilder.success(HttpStatus.OK,"New User Details has been added" ,userDetails);
//    }

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<UserDetails>> addUser(
            @RequestBody UserResponse dto) {

        UserDetails savedUser = userService.addUser(dto);
        return responseBuilder.success(HttpStatus.OK, "User registered successfully", savedUser);
    }

}
