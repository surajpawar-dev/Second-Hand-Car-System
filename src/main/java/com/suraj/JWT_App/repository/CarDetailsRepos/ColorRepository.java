package com.suraj.JWT_App.repository.CarDetailsRepos;

import com.suraj.JWT_App.entity.car_details.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColorRepository extends JpaRepository<Color, Long> {

    Optional<Color> findByColor(String color);
}