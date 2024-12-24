package com.example.car.CarService.dto;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SavedAddressDto {
    private int sid;
    private String addressNickName;
    private String latitude;
    private String longitude;
    private int pincode;
    private String city;
    private String State;
    private String nation;
    private int userId; // Reference to UserDetails by ID
}
