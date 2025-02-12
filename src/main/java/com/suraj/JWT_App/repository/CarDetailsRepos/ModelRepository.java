package com.suraj.JWT_App.repository.CarDetailsRepos;

import com.suraj.JWT_App.entity.car_details.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Long> {
   Optional<Model> findByModelName(String modelName);
}