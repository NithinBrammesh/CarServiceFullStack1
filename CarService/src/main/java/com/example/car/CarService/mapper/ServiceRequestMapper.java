package com.example.car.CarService.mapper;

import com.example.car.CarService.dto.ServiceRequestDto;
import com.example.car.CarService.model.ServiceRequest;

public class ServiceRequestMapper {

    public static ServiceRequest mapToServiceRequest(ServiceRequestDto serviceRequestDto) {
        return new ServiceRequest(
                serviceRequestDto.getRequestId(),
                serviceRequestDto.getDate(),
                serviceRequestDto.getCar(),
                serviceRequestDto.getCaruserDetails(),
                serviceRequestDto.getSavedAddress(),
                serviceRequestDto.getStatus(),
                serviceRequestDto.getPayment()
        );
    }

    public static ServiceRequestDto mapToServiceRequestDto(ServiceRequest serviceRequest) {
        return new ServiceRequestDto(
                serviceRequest.getRequestId(),
                serviceRequest.getDate(),
                serviceRequest.getCar(),
                serviceRequest.getCaruserDetails(),
                serviceRequest.getSavedAddress(),
                serviceRequest.getStatus(),
                serviceRequest.getPayment()
        );
    }
}
