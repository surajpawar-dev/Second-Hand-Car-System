package com.suraj.JWT_App.service;

import com.suraj.JWT_App.Exception.ResourceNotFound;
import com.suraj.JWT_App.entity.car_details.*;
import com.suraj.JWT_App.helper.EntityFinderHelper;
import com.suraj.JWT_App.payload.CarDetailsDTOs.CarDTO;
import com.suraj.JWT_App.repository.CarDetailsRepos.CarRepository;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class CarService {

    private final EntityFinderHelper helper;
    private final CarRepository carRepository;
    private final ModelMapper mapper;

    @Autowired
    private EntityManager entityManager;

    public CarService(EntityFinderHelper helper, CarRepository carRepository, ModelMapper mapper) {
        this.helper = helper;
        this.carRepository = carRepository;
        this.mapper = mapper;
    }


    public CarDTO addCar(CarDTO dto) {
        Car car = mapper.map(dto, Car.class);

        car.setBrand(helper.findOrCreateBrand(dto.getBrand()));
        car.setColor(helper.findOrCreateColor(dto.getColor()));
        car.setFuelType(helper.findOrCreateFuelType(dto.getFuelType()));
        car.setTransmissionType(helper.findOrCreateTransmissionType(dto.getTransmissionType()));
        car.setYearOfManufacture(helper.findOrCreateYear(dto.getYearOfManufacture()));
        car.setModel(helper.findOrCreateModel(dto.getModel()));


        Car saved = carRepository.save(car);
        return mapper.map(saved, CarDTO.class);

    }

    public List<CarDTO> getAllCarDetails() {
        List<Car> carList = carRepository.findAll();

        return carList
                .stream()
                .map(car -> mapper.map(car, CarDTO.class))
                .collect(Collectors.toList());

    }

    public String deleteCar(Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return "Deleted";
        } else {
            throw new ResourceNotFound("Car not found with id: " + id);
        }
    }

    public CarDTO updateCar(Long id, CarDTO carDTO) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Car not found with id: " + id));

        car.setBrand(helper.findOrCreateBrand(carDTO.getBrand()));
        car.setColor(helper.findOrCreateColor(carDTO.getColor()));
        car.setDescription(carDTO.getDescription());
        car.setFuelType(helper.findOrCreateFuelType(carDTO.getFuelType()));
        car.setPrice(carDTO.getPrice());
        car.setTransmissionType(helper.findOrCreateTransmissionType(carDTO.getTransmissionType()));
        car.setYearOfManufacture(helper.findOrCreateYear(carDTO.getYearOfManufacture()));
        car.setModel(helper.findOrCreateModel(carDTO.getModel()));

        Car updated = carRepository.save(car);
        return mapper.map(updated, CarDTO.class);
    }

    public CarDTO getCarById(Long id) {
        return carRepository.findById(id)
               .map(car -> mapper.map(car, CarDTO.class))
               .orElseThrow(() -> new ResourceNotFound("Car not found with id: " + id));

        //    return carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car not found with id: " + id));

    }


    //    convert carDetailsDTO to car entity

}
