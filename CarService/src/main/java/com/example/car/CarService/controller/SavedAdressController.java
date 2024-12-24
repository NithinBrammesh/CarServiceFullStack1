package com.example.car.CarService.controller;

import com.example.car.CarService.SavedAddressService;
import com.example.car.CarService.service.Impl.SavedAddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.car.CarService.dto.SavedAddressDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/saved_addresses")
@CrossOrigin(origins = "http://127.0.0.1:5500/CarServiceFrontend/SavedAddress.html")
public class SavedAdressController {
    @Autowired
    private SavedAddressServiceImpl savedAddressServiceImpl;

    public SavedAdressController(SavedAddressServiceImpl savedAddressServiceImpl) {
        this.savedAddressServiceImpl = savedAddressServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<SavedAddressDto>> getAllSavedAddresses() {
        List<SavedAddressDto> savedAddressDtos = savedAddressServiceImpl.getAllSavedAddresses();
        return ResponseEntity.ok(savedAddressDtos);
    }

    @PostMapping
    public ResponseEntity<SavedAddressDto> addSavedAddress(@RequestBody SavedAddressDto savedAddressDto) {
        SavedAddressDto createdAddress = savedAddressServiceImpl.createSavedAddress(savedAddressDto);
        return ResponseEntity.ok(createdAddress); // Status CREATED
    }

    @GetMapping("/{id}")
    public ResponseEntity<SavedAddressDto> getSavedAddressById(@PathVariable("id") int id) {
        SavedAddressDto savedAddressDto = savedAddressServiceImpl.getSavedAddressById(id);
        return  ResponseEntity.ok(savedAddressDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SavedAddressDto> updateSavedAddress(@PathVariable int id, @RequestBody SavedAddressDto savedAddressDto) {
        SavedAddressDto updatedAddress = savedAddressServiceImpl.updateSavedAddress(id, savedAddressDto);
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSavedAddress(@PathVariable int id) {
        savedAddressServiceImpl.deleteSavedAddress(id);
        return ResponseEntity.noContent().build(); // Status NO_CONTENT
    }




}
