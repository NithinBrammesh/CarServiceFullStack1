package com.example.car.CarService;

import com.example.car.CarService.dto.UserDetailsDto;

import java.util.List;

public interface CarUserDetailsService {
    UserDetailsDto createUserDetails(UserDetailsDto userDetailsDto);

    List<UserDetailsDto> getAllUserDetails();

    UserDetailsDto getUserDetailsById(int Id);

    UserDetailsDto updateUserDetail(int Id, UserDetailsDto userDetailsDto);

    UserDetailsDto deleteUserDetailsById(int userId);
}
