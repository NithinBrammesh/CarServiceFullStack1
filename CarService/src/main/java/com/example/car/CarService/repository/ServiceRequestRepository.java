package com.example.car.CarService.repository;

import com.example.car.CarService.model.Car;
import com.example.car.CarService.model.ServiceRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Integer> {
//    @EntityGraph(attributePaths = {"car", "caruserDetails", "savedAddress"})
//    List<ServiceRequest> findAll();
//
//    // Fetch ServiceRequest by Car (with the carUserDetails and savedAddress also fetched)
//    @EntityGraph(attributePaths = {"car", "caruserDetails", "savedAddress"})
//    List<ServiceRequest> findByCar(Car car);
}
