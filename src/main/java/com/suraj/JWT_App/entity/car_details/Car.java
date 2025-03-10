package com.suraj.JWT_App.entity.car_details;

import com.suraj.JWT_App.entity.evaluation.ImageEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "description", length = 500)
    private String description;

    @ManyToOne()
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;

    @ManyToOne()
    @JoinColumn(name = "transmission_type_id", nullable = false)
    private TransmissionType transmissionType;

    @ManyToOne()
    @JoinColumn(name = "fuel_type_id", nullable = false)
    private FuelType fuelType;

    @ManyToOne()
    @JoinColumn(name = "year_of_manufacture_id", nullable = false)
    private YearOfManufacture yearOfManufacture;

    @ManyToOne()
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @ManyToOne()
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @OneToMany(mappedBy = "car", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ImageEntity> imageEntities = new ArrayList<>();

    public List<ImageEntity> getImageEntities() {
        return imageEntities;
    }

    public void setImageEntities(List<ImageEntity> imageEntities) {
        this.imageEntities = imageEntities;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public YearOfManufacture getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(YearOfManufacture yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }
}
