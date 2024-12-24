package com.example.car.CarService.service.Impl;

import com.example.car.CarService.ServiceRequestService;
import com.example.car.CarService.dto.ServiceRequestDto;
import com.example.car.CarService.mapper.ServiceRequestMapper;
import com.example.car.CarService.model.ServiceRequest;
import com.example.car.CarService.repository.ServiceRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceRequestServiceImpl implements ServiceRequestService {

    private final ServiceRequestRepository serviceRequestRepository;

    public ServiceRequestServiceImpl(ServiceRequestRepository serviceRequestRepository) {
        this.serviceRequestRepository = serviceRequestRepository;
    }

    public List<ServiceRequestDto> getAllServiceRequest() {
        return serviceRequestRepository.findAll()
                .stream()
                .map(ServiceRequestMapper::mapToServiceRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceRequestDto createServiceRequest(ServiceRequestDto serviceRequestDto) {
        ServiceRequest serviceRequest = ServiceRequestMapper.mapToServiceRequest(serviceRequestDto);
        ServiceRequest savedServiceRequest = serviceRequestRepository.save(serviceRequest);
        return ServiceRequestMapper.mapToServiceRequestDto(savedServiceRequest);
    }

    @Override
    public ServiceRequestDto getServiceRequestById(int id) {
        ServiceRequest serviceRequest = serviceRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ServiceRequest not found"));
        return ServiceRequestMapper.mapToServiceRequestDto(serviceRequest);
    }

    @Override
    public ServiceRequestDto updateServiceRequest(int id, ServiceRequestDto serviceRequestDto) {
        ServiceRequest existingServiceRequest = serviceRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ServiceRequest not found"));

        existingServiceRequest.setDate(serviceRequestDto.getDate());
        existingServiceRequest.setCar(serviceRequestDto.getCar());
        existingServiceRequest.setCaruserDetails(serviceRequestDto.getCaruserDetails());
        existingServiceRequest.setSavedAddress(serviceRequestDto.getSavedAddress());
        existingServiceRequest.setStatus(serviceRequestDto.getStatus());
        existingServiceRequest.setPayment(serviceRequestDto.getPayment());

        ServiceRequest updatedServiceRequest = serviceRequestRepository.save(existingServiceRequest);
        return ServiceRequestMapper.mapToServiceRequestDto(updatedServiceRequest);
    }

    @Override
    public ServiceRequestDto deleteServiceAddress(int id) {
        ServiceRequest serviceRequest = serviceRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service request does not exist"));
        ServiceRequestDto serviceRequestDto = ServiceRequestMapper.mapToServiceRequestDto(serviceRequest);
        serviceRequestRepository.deleteById(id);
        return serviceRequestDto;
    }
}
