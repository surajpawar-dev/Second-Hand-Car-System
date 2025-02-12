package com.suraj.JWT_App.payload.CarDetailsDTOs;

import com.suraj.JWT_App.entity.car_details.Brand;

public class CarDTO {

    private Long id;
    private double price;
    private String description;
    private ColorDTO color;
    private TransmissionTypeDTO transmissionType;
    private FuelTypeDTO fuelType;
    private YearOfManufactureDTO yearOfManufacture;
    private BrandDTO brand;
    private ModelDTO model;



    public ModelDTO getModel() {
        return model;
    }

    public void setModel(ModelDTO model) {
        this.model = model;
    }

    public BrandDTO getBrand() {
        return brand;
    }

    public void setBrand(BrandDTO brand) {
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ColorDTO getColor() {
        return color;
    }

    public void setColor(ColorDTO color) {
        this.color = color;
    }

    public TransmissionTypeDTO getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionTypeDTO transmissionType) {
        this.transmissionType = transmissionType;
    }

    public FuelTypeDTO getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelTypeDTO fuelType) {
        this.fuelType = fuelType;
    }

    public YearOfManufactureDTO getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(YearOfManufactureDTO yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

}
