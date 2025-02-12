package com.suraj.JWT_App.repository.CarDetailsRepos;

import com.suraj.JWT_App.entity.car_details.TransmissionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransmissionTypeRepository extends JpaRepository<TransmissionType, Long> {
    Optional<TransmissionType> findByTransType(String transType);
}