package com.suraj.JWT_App.repository.CarDetailsRepos;

import com.suraj.JWT_App.entity.car_details.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {


    Optional<Brand> findByBrandName(String brandName);
}