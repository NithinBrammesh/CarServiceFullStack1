package com.example.car.CarService.repository;

import com.example.car.CarService.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
