package com.suraj.JWT_App.repository.evaluation;

import com.suraj.JWT_App.entity.evaluation.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AreaRepository extends JpaRepository<Area, Long> {
}