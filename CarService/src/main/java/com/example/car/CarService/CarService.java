package com.example.car.CarService;

import com.example.car.CarService.dto.CarDto;
import com.example.car.CarService.dto.UserDetailsDto;

import java.util.List;

public interface CarService {
    CarDto createCar (CarDto carDto);
    List<CarDto> getAllCars();
    CarDto getCarById(int Id);
    CarDto updateCar(int Id, CarDto carDto);
    CarDto deleteCarById(int Id);
}
