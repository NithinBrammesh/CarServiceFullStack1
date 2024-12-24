package com.example.car.CarService.service.Impl;
import com.example.car.CarService.model.Car;
import com.example.car.CarService.dto.CarDto;
import com.example.car.CarService.CarService;
import com.example.car.CarService.mapper.CarMapper;
import com.example.car.CarService.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public CarDto createCar(CarDto carDto) {
        Car car = CarMapper.mapToCar(carDto);
        Car savedAccount = carRepository.save(car);
        return CarMapper.mapToCarDto(savedAccount);
    }

    @Override
    public List<CarDto> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(CarMapper::mapToCarDto)
                .collect(Collectors.toList());
    }

    @Override
    public CarDto getCarById(int id){
       Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car does not exist"));
       return CarMapper.mapToCarDto(car);
    }

    @Override
    public CarDto updateCar(int Id, CarDto carDto) {
        // Fetch the existing car by ID
        Car existingCar = carRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("Car does not exist"));

        if (carDto.getCarType() != null) {
            existingCar.setCarType(carDto.getCarType());
        }


        existingCar.setPrice(carDto.getPrice());

    // Save the updated car
    Car updatedCar = carRepository.save(existingCar);

    // Convert the updated car entity back to a DTO
        return new CarDto(updatedCar.getId(), updatedCar.getCarType(), updatedCar.getPrice());
     }

    @Override
    public CarDto deleteCarById(int id) {
        // Check if the car exists, throw an exception if not
        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car does not exist"));

        // Map to CarDto for returning before deletion
        CarDto carDto = CarMapper.mapToCarDto(car);

        // Delete the car
        carRepository.deleteById(id);

        // Return the CarDto after deletion
        return carDto;
    }
}
