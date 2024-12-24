package com.example.car.CarService.repository;

import com.example.car.CarService.model.SavedAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedAddressRepository extends JpaRepository<SavedAddress, Integer> {
}
