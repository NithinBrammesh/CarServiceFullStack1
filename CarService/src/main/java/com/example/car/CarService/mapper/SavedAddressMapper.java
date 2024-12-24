package com.example.car.CarService.mapper;

import com.example.car.CarService.dto.CarDto;
import com.example.car.CarService.dto.SavedAddressDto;
import com.example.car.CarService.model.Car;
import com.example.car.CarService.model.SavedAddress;
import com.example.car.CarService.model.carUserDetails;

public class SavedAddressMapper {

  public static SavedAddress mapToSavedAddress(SavedAddressDto savedAddressDto, carUserDetails caruserDetails) {
        SavedAddress savedAddress = new SavedAddress(
                savedAddressDto.getSid(),
                savedAddressDto.getAddressNickName(),
                savedAddressDto.getLatitude(),
                savedAddressDto.getLongitude(),
                savedAddressDto.getPincode(),
                savedAddressDto.getCity(),
                savedAddressDto.getState(),
                savedAddressDto.getNation(),
                caruserDetails
        );
        return savedAddress;

    }
    public static SavedAddressDto mapToSavedAddressDto(SavedAddress savedAddress) {
        SavedAddressDto savedAddressDto = new SavedAddressDto();

        savedAddressDto.setSid(savedAddress.getSid());
        savedAddressDto.setAddressNickName(savedAddress.getAddressNickName());
        savedAddressDto.setLatitude(savedAddress.getLatitude());
        savedAddressDto.setLongitude(savedAddress.getLongitude());
        savedAddressDto.setPincode(savedAddress.getPincode());
        savedAddressDto.setCity(savedAddress.getCity());
        savedAddressDto.setState(savedAddress.getState());
        savedAddressDto.setNation(savedAddress.getNation());

        // Map UserDetails' ID to userId in the DTO
        if (savedAddress.getCarUserDetails() != null) {
            savedAddressDto.setUserId(savedAddress.getCarUserDetails().getUserId());
        }

        return savedAddressDto;
    }

}
