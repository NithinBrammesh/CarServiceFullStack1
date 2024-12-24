package com.example.car.CarService;

import com.example.car.CarService.dto.ServiceRequestDto;
import com.example.car.CarService.model.ServiceRequest;

import java.util.List;

public interface ServiceRequestService {
    List<ServiceRequestDto> getAllServiceRequest();

    ServiceRequestDto createServiceRequest (ServiceRequestDto serviceRequestDto);

    ServiceRequestDto getServiceRequestById (int Id);

    ServiceRequestDto updateServiceRequest (int Id ,ServiceRequestDto serviceRequestDto);

    ServiceRequestDto deleteServiceAddress(int id);
}
