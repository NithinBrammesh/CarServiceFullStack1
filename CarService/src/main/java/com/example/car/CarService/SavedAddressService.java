package com.example.car.CarService;

import com.example.car.CarService.dto.SavedAddressDto;

import java.util.List;

public interface SavedAddressService {

    List<SavedAddressDto> getAllSavedAddresses();

    SavedAddressDto createSavedAddress(SavedAddressDto savedAddressDto);

    SavedAddressDto getSavedAddressById(int id);

    SavedAddressDto updateSavedAddress(int id, SavedAddressDto savedAddressDto);

    SavedAddressDto deleteSavedAddress(int id);
}
