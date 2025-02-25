package com.suraj.JWT_App.controller;

import com.suraj.JWT_App.payload.Evaluation.CarDetailedEvaluationDTO;
import com.suraj.JWT_App.service.CarEvaluationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carEvaluation")
public class CarEvaluationController {

    private final CarEvaluationService carEvaluationService;

    public CarEvaluationController(CarEvaluationService carEvaluationService) {
        this.carEvaluationService = carEvaluationService;
    }

    // Create a new car evaluation
    @PostMapping
    public ResponseEntity<CarDetailedEvaluationDTO> addCarEvaluation(@RequestBody CarDetailedEvaluationDTO evaluationDTO) {
        CarDetailedEvaluationDTO carDetailedEvaluationDTO = carEvaluationService.addCarEvaluation(evaluationDTO);
        return new ResponseEntity<>(carDetailedEvaluationDTO, HttpStatus.CREATED);
    }

    // Get all car evaluations
    @GetMapping
    public ResponseEntity<List<CarDetailedEvaluationDTO>> getAllCarEvaluations() {
        List<CarDetailedEvaluationDTO> evaluations = carEvaluationService.getAllCarEvaluations();
        return ResponseEntity.ok(evaluations);
    }

    // Get a specific car evaluation by ID
    @GetMapping("/{id}")
    public ResponseEntity<CarDetailedEvaluationDTO> getCarEvaluationById(@PathVariable Long id) {
        CarDetailedEvaluationDTO evaluationDTO = carEvaluationService.getCarEvaluationById(id);
        return ResponseEntity.ok(evaluationDTO);
    }

    // Update an existing car evaluation
    @PutMapping("/{id}")
    public ResponseEntity<CarDetailedEvaluationDTO> updateCarEvaluation(
            @PathVariable Long id,
            @RequestBody CarDetailedEvaluationDTO evaluationDTO) {
        CarDetailedEvaluationDTO updatedEvaluation = carEvaluationService.updateCarEvaluation(id, evaluationDTO);
        return ResponseEntity.ok(updatedEvaluation);
    }

    // Delete a car evaluation
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCarEvaluation(@PathVariable Long id) {
        carEvaluationService.deleteCarEvaluation(id);
        return ResponseEntity.ok("Car evaluation deleted successfully.");
    }
}
