package com.example.car.CarService.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {
    private int userId;

    private String name;

    private Long phoneNo;

    private String email;

    private String password;
}
