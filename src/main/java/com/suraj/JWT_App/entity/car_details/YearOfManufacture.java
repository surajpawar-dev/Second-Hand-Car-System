package com.suraj.JWT_App.entity.car_details;

import jakarta.persistence.*;

@Entity
@Table(name = "year_of_manufacture")
public class YearOfManufacture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "year", unique = true)
    private String year;

    public YearOfManufacture() {}

    public YearOfManufacture(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}