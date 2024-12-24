package com.example.car.CarService.service.Impl;

import com.example.car.CarService.config.SecurityConfig;
import com.example.car.CarService.dto.UserDetailsDto;
import com.example.car.CarService.mapper.UserDetailsMapper;
import com.example.car.CarService.model.carUserDetails;
import com.example.car.CarService.repository.UserDetailsRepository;
import com.example.car.CarService.CarUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsImpl implements CarUserDetailsService, UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;
    private final BCryptPasswordEncoder encoder;
//    private final SecurityConfig securityConfig;


//    public UserDetailsImpl(UserDetailsRepository userDetailsRepository) {
//        this.userDetailsRepository = userDetailsRepository;
//    }

    @Autowired
    public UserDetailsImpl(UserDetailsRepository userDetailsRepository, BCryptPasswordEncoder encoder) { //, @Lazy SecurityConfig securityConfig)
        this.userDetailsRepository = userDetailsRepository;
        this.encoder = encoder;
//        this.securityConfig = securityConfig;
    }


    // Override loadUserByUsername to use 'name' as the username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user by name (username) from the repository
        carUserDetails userDetails = userDetailsRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with name: " + username));

        // Return a Spring Security User object
        return User.builder()
                .username(userDetails.getName())   // Use 'name' as the username
                .password(userDetails.getPassword()) // Ensure passwords are hashed in production
                .roles("USER") // Assign default role
                .build();
    }

    @Override
    public UserDetailsDto createUserDetails(UserDetailsDto userDetailsDto) {
        userDetailsDto.setPassword(encoder.encode(userDetailsDto.getPassword())); // Encode password
        carUserDetails userDetails = UserDetailsMapper.mapToUserDetails(userDetailsDto);
        carUserDetails savedUser = userDetailsRepository.save(userDetails);
        return UserDetailsMapper.mapToUserDetailDto(savedUser);
    }

//    @Override
//    public UserDetailsDto createUserDetails(UserDetailsDto userDetailsDto) {
//        carUserDetails userDetails = UserDetailsMapper.mapToUserDetails(userDetailsDto);
//        carUserDetails savedUser = userDetailsRepository.save(userDetails);
//        return UserDetailsMapper.mapToUserDetailDto(savedUser);
//    }

    @Override
    public List<UserDetailsDto> getAllUserDetails() {
        List<carUserDetails> userDetails = userDetailsRepository.findAll();
        return userDetails.stream()
                .map(UserDetailsMapper::mapToUserDetailDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetailsDto getUserDetailsById(int Id) {
        carUserDetails userDetails = userDetailsRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("UserDetails does not exist"));
        return UserDetailsMapper.mapToUserDetailDto(userDetails);
    }

    @Override
    public UserDetailsDto updateUserDetail(int id, UserDetailsDto userDetailsDto) {
        carUserDetails existingUser = userDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User does not exist"));

        if (userDetailsDto.getPassword() != null) {
            existingUser.setPassword(userDetailsDto.getPassword());
        }

        if (userDetailsDto.getEmail() != null) {
            existingUser.setEmail(userDetailsDto.getEmail());
        }

        if (userDetailsDto.getPhoneNo() != null) {
            existingUser.setPhoneNo(userDetailsDto.getPhoneNo());
        }

        if (userDetailsDto.getName() != null) {
            existingUser.setName(userDetailsDto.getName());
        }

        carUserDetails updatedUser = userDetailsRepository.save(existingUser);
        return UserDetailsMapper.mapToUserDetailDto(updatedUser);
    }

    @Override
    public UserDetailsDto deleteUserDetailsById(int userId) {
        carUserDetails userDetails = userDetailsRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User does not exist"));

        UserDetailsDto userDetailsDto = UserDetailsMapper.mapToUserDetailDto(userDetails);
        userDetailsRepository.deleteById(userId);

        return userDetailsDto;
    }
}
