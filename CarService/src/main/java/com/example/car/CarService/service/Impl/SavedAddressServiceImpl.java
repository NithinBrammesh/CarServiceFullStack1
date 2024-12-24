package com.example.car.CarService.service.Impl;

import com.example.car.CarService.SavedAddressService;
import com.example.car.CarService.model.carUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.car.CarService.dto.SavedAddressDto;
import com.example.car.CarService.mapper.SavedAddressMapper;
import com.example.car.CarService.model.SavedAddress;
import com.example.car.CarService.repository.SavedAddressRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SavedAddressServiceImpl implements SavedAddressService {
    @Autowired
    private  SavedAddressRepository savedAddressRepository;

    public SavedAddressServiceImpl(SavedAddressRepository savedAddressRepository) {
        this.savedAddressRepository = savedAddressRepository;
    }
    @Override
    public List<SavedAddressDto> getAllSavedAddresses() {
        return savedAddressRepository.findAll()
                .stream()
                .map(SavedAddressMapper::mapToSavedAddressDto)
                .collect(Collectors.toList());
    }
    @Override
    public SavedAddressDto createSavedAddress(SavedAddressDto savedAddressDto) {
        SavedAddress savedAddress = new SavedAddress();
        savedAddress.setAddressNickName(savedAddressDto.getAddressNickName());
        savedAddress.setLatitude(savedAddressDto.getLatitude());
        savedAddress.setLongitude(savedAddressDto.getLongitude());
        savedAddress.setPincode(savedAddressDto.getPincode());
        savedAddress.setCity(savedAddressDto.getCity());
        savedAddress.setState(savedAddressDto.getState());
        savedAddress.setNation(savedAddressDto.getNation());
        // Set the user by userId
        savedAddress.setCarUserDetails(new carUserDetails(savedAddressDto.getUserId()));

        SavedAddress saved = savedAddressRepository.save(savedAddress);
        return SavedAddressMapper.mapToSavedAddressDto(saved);
    }

    @Override
    public SavedAddressDto getSavedAddressById(int id) {
        return savedAddressRepository.findById(id)
                .map(SavedAddressMapper::mapToSavedAddressDto)
                .orElse(null);
    }

    public SavedAddressDto updateSavedAddress(int id, SavedAddressDto savedAddressDto) {
        return savedAddressRepository.findById(id)
                .map(existingAddress -> {
                    existingAddress.setAddressNickName(savedAddressDto.getAddressNickName());
                    existingAddress.setLatitude(savedAddressDto.getLatitude());
                    existingAddress.setLongitude(savedAddressDto.getLongitude());
                    existingAddress.setPincode(savedAddressDto.getPincode());
                    existingAddress.setCity(savedAddressDto.getCity());
                    existingAddress.setState(savedAddressDto.getState());
                    existingAddress.setNation(savedAddressDto.getNation());
                    savedAddressRepository.save(existingAddress);
                    return SavedAddressMapper.mapToSavedAddressDto(existingAddress);
                })
                .orElse(null);
    }

    @Override
    public SavedAddressDto deleteSavedAddress(int id) {

        SavedAddress savedAddress = savedAddressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Saved Address does not exist"));

        SavedAddressDto savedAddressDto = SavedAddressMapper.mapToSavedAddressDto(savedAddress);

        savedAddressRepository.deleteById(id);

        return savedAddressDto;
    }
}


