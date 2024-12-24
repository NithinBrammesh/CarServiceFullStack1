package com.example.car.CarService.controller;

import com.example.car.CarService.CarService;
import com.example.car.CarService.dto.CarDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin(origins = "http://localhost:9003")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }
    @RequestMapping
    public ResponseEntity<List<CarDto>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @PostMapping
    ResponseEntity<CarDto> AddCar(@RequestBody CarDto carDto){
        return new ResponseEntity<>(carService.createCar(carDto),HttpStatus.CREATED);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable("Id") int Id){
        CarDto carDto = carService.getCarById(Id);
        return ResponseEntity.ok(carDto);   //OK METHOD
    }

    @PutMapping("/{Id}")
    public ResponseEntity<CarDto> updateCar(@PathVariable int Id, @RequestBody CarDto carDto) {
        CarDto updatedCar = carService.updateCar(Id, carDto);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<CarDto> deleteCarById(@PathVariable("Id") int id) {
         CarDto deleteCarById = carService.deleteCarById(id);
        return ResponseEntity.ok(deleteCarById);
    }
}