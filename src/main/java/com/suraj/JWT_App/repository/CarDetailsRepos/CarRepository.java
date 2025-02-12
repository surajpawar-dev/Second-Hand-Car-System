package com.suraj.JWT_App.repository.CarDetailsRepos;

import com.suraj.JWT_App.entity.car_details.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}