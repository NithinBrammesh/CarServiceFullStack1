package com.example.car.CarService.repository;

import com.example.car.CarService.model.carUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<carUserDetails, Integer> {
    Optional<carUserDetails> findByName(String name);  // Use 'name' here
}

