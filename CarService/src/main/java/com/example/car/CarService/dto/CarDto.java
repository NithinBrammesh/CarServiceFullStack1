package com.example.car.CarService.dto;

import com.example.car.CarService.model.Car;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private int Id;

    private String CarType;

    private int Price;

}
