package com.example.car.CarService.mapper;
import com.example.car.CarService.dto.CarDto;
import com.example.car.CarService.model.Car;

public class CarMapper {

   public static Car mapToCar(CarDto carDto){
       Car car = new Car(
           carDto.getId(),
           carDto.getCarType(),
           carDto.getPrice()
       );
     return car;
   }

   public static CarDto mapToCarDto(Car car){
       CarDto carDto = new CarDto(
               car.getId(),
               car.getCarType(),
               car.getPrice()
       );
       return carDto;
   }


}
