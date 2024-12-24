package com.example.car.CarService.controller;

import com.example.car.CarService.CarUserDetailsService;
import com.example.car.CarService.SavedAddressService;
import com.example.car.CarService.CarUserDetailsService;
import com.example.car.CarService.dto.UserDetailsDto;
import com.example.car.CarService.service.Impl.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userDetails")
//@CrossOrigin(origins = {
//        "http://127.0.0.1:5500/CarServiceFrontend/loginPage1.html",
//        "http://127.0.0.1:5500/CarServiceFrontend/UserLogin.html"
//})
public class UserDetailsController {

    private CarUserDetailsService carUserDetailsService;

    public UserDetailsController(CarUserDetailsService carUserDetailsService) {
        this.carUserDetailsService = carUserDetailsService;
    }

    @PostMapping
    ResponseEntity<UserDetailsDto> AddUserDetails(@RequestBody UserDetailsDto userDetailsDto) {
        return new ResponseEntity<>(carUserDetailsService.createUserDetails(userDetailsDto), HttpStatus.CREATED);
    }

    @RequestMapping
    public ResponseEntity<List<UserDetailsDto>> getAllUserDetails() {
        return ResponseEntity.ok(carUserDetailsService.getAllUserDetails());
    }

    @GetMapping("/{User_id}")
    public ResponseEntity<UserDetailsDto> getUserDetailsById(@PathVariable("User_id") int Id) {
        UserDetailsDto userDetailsDto = carUserDetailsService.getUserDetailsById(Id);
        return ResponseEntity.ok(userDetailsDto);
    }

    @PutMapping("/{User_id}")
    public ResponseEntity<UserDetailsDto> updateUserDetails(@PathVariable int Id, @RequestBody UserDetailsDto userDetailsDto) {
        UserDetailsDto updateDetailsDto = carUserDetailsService.updateUserDetail(Id, userDetailsDto);
        return ResponseEntity.ok(updateDetailsDto);
    }

    @DeleteMapping("/{User_id}")
    public ResponseEntity<UserDetailsDto> deleteUserDetailsById(@PathVariable("User_id") int userId) {
        UserDetailsDto deleteUserDetailsById = carUserDetailsService.deleteUserDetailsById(userId);
        return ResponseEntity.ok(deleteUserDetailsById);
    }
}

