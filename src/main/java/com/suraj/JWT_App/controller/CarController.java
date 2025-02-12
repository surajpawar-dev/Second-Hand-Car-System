package com.suraj.JWT_App.controller;

import com.suraj.JWT_App.payload.CarDetailsDTO;
import com.suraj.JWT_App.payload.CarDetailsDTOs.CarDTO;
import com.suraj.JWT_App.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/add-car")
    public ResponseEntity<CarDTO> addCar(@RequestBody CarDTO carDTO){
        // Add car details to database

        CarDTO savedDto = carService.addCar(carDTO);

        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    @GetMapping("/all-cars")
    public ResponseEntity<List<CarDTO>> getAllCarDetails(){
        // Fetch car details from database
        List<CarDTO> allCarDetails = carService.getAllCarDetails();
        return new ResponseEntity<>(allCarDetails, HttpStatus.OK);
    }

    @DeleteMapping("/delete-car/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id){
        String message = carService.deleteCar(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/update-car/{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable Long id, @RequestBody CarDTO carDTO){
        // Update car details in database
        CarDTO updatedDto = carService.updateCar(id,carDTO);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @GetMapping("/get-car-by-id/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id){
        CarDTO carDTO = carService.getCarById(id);
        return new ResponseEntity<>(carDTO, HttpStatus.OK);
    }

}
