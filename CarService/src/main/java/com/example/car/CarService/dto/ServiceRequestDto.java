package com.example.car.CarService.dto;

import com.example.car.CarService.model.Status;
import com.example.car.CarService.model.Car;
import com.example.car.CarService.model.SavedAddress;
import com.example.car.CarService.model.carUserDetails;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequestDto {

    private int requestId;
    private String date;
    private Car car;
    private carUserDetails caruserDetails;
    private SavedAddress savedAddress;
    private Status status;
    private int payment;
}
