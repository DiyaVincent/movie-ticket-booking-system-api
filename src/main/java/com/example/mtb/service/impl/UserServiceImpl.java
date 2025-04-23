package com.example.mtb.service.impl;

import com.example.mtb.dto.UserRegistrationDTO;
import com.example.mtb.entity.UserDetails;
import com.example.mtb.exceptions.UserExistByEmailException;
import com.example.mtb.mapper.UserDetailsMapper;
import com.example.mtb.repository.UserRepository;
import com.example.mtb.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

//@Service
//@AllArgsConstructor
//public class UserServiceImpl implements UserService {
//
//    private final UserRepository userRepository;
//    private final UserDetailsMapper userMapper;
//
//    @Override
//    public UserRegistrationDTO addUser(UserRegistrationRequest user)  {
//
//        if(userRepository.existsByEmail(user.getEmail())){
//            throw new UserExistByEmailException("user with email already exists");
//         //   return copy(user);
//            UserDetails userDetails = switch (user.getUserRole()) {
//                case USER -> copy(new User(), user);
//                case THEATER_OWNER -> copy(new TheaterOwner(),user);
//            };
//      //      System.out.println(user);
//      //      return userDetails;
//            return userMapper.userDetailsResponseMapper(userDetails);
//        }
//
//
//    }
//
//    private UserDetails copy(UserDetails userRole,UserDetails user){
//        //UserDetails userRole = user.getUserRole()==UserRole.USER ? new User(): new TheaterOwner();
//        userRole.setUserRole(user.getUserRole());
//        userRole.setEmail(user.getEmail());
//        userRole.setPassword(user.getPassword());
//        userRole.setCreatedAt(user.getCreatedAt());
//        userRole.setDateOfBirth(user.getDateOfBirth());
//        userRole.setPhoneNumber(user.getPhoneNumber());
//        userRole.setUsername(user.getUsername());
//        userRole.setUpdatedAt(user.getUpdatedAt());
//        userRepository.save(userRole);
//        return userRole;
//    }
//}


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails addUser(UserRegistrationDTO dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new UserExistByEmailException("User with email already exists");
        }

        UserDetails user = UserDetailsMapper.mapFromDTO(dto);
        return userRepository.save(user);
    }
}