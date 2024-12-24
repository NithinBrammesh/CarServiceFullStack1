package com.example.car.CarService.mapper;

import com.example.car.CarService.dto.UserDetailsDto;
import com.example.car.CarService.model.carUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
//import com.example.car.CarService.model.UserDetails;

public class UserDetailsMapper {

    public static carUserDetails mapToUserDetails(UserDetailsDto userDetailsDto) {
        return new carUserDetails(
                userDetailsDto.getUserId(),
                userDetailsDto.getName(),
                userDetailsDto.getPhoneNo(),
                userDetailsDto.getEmail(),
                userDetailsDto.getPassword()
        );
    }

    public static UserDetailsDto mapToUserDetailDto(carUserDetails userDetails) {
        return new UserDetailsDto(
                userDetails.getUserId(),
                userDetails.getName(),
                userDetails.getPhoneNo(),
                userDetails.getEmail(),
                userDetails.getPassword()
        );
    }


//    public static UserDetailsDto mapToUserDetailDto(UserDetails userDetails){
//        UserDetailsDto userDetailsDto  = new UserDetailsDto(
//                userDetails.getUserId(),
//                userDetails.getName(),
//                userDetails.getPhoneNo(),
//                userDetails.getEmail(),
//                userDetails.getPassword()
//        );
//        return userDetailsDto;
//  }

}
