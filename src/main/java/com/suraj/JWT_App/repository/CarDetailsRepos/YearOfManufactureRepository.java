package com.suraj.JWT_App.repository.CarDetailsRepos;

import com.suraj.JWT_App.entity.car_details.YearOfManufacture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface YearOfManufactureRepository extends JpaRepository<YearOfManufacture, Long> {
    Optional<YearOfManufacture> findByYear(String year);
}