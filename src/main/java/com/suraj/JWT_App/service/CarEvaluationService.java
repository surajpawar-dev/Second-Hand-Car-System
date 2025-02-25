package com.suraj.JWT_App.service;

import com.suraj.JWT_App.entity.evaluation.CarDetailedEvaluation;
import com.suraj.JWT_App.payload.Evaluation.CarDetailedEvaluationDTO;
import com.suraj.JWT_App.repository.evaluation.CarDetailedEvaluationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarEvaluationService {

    private final CarDetailedEvaluationRepository carDetailedEvaluationRepository;
    private final ModelMapper modelMapper;

    public CarEvaluationService(CarDetailedEvaluationRepository carDetailedEvaluationRepository, ModelMapper modelMapper) {
        this.carDetailedEvaluationRepository = carDetailedEvaluationRepository;
        this.modelMapper = modelMapper;
    }

    // Add a new car evaluation
    public CarDetailedEvaluationDTO addCarEvaluation(CarDetailedEvaluationDTO evaluationDTO) {
        CarDetailedEvaluation detailedEvaluation = modelMapper.map(evaluationDTO, CarDetailedEvaluation.class);
        CarDetailedEvaluation evaluation = carDetailedEvaluationRepository.save(detailedEvaluation);
        return modelMapper.map(evaluation, CarDetailedEvaluationDTO.class);
    }

    // Get all car evaluations
    public List<CarDetailedEvaluationDTO> getAllCarEvaluations() {
        List<CarDetailedEvaluation> evaluations = carDetailedEvaluationRepository.findAll();
        return evaluations.stream()
                .map(evaluation -> modelMapper.map(evaluation, CarDetailedEvaluationDTO.class))
                .collect(Collectors.toList());
    }

    // Get a single car evaluation by ID
    public CarDetailedEvaluationDTO getCarEvaluationById(Long id) {
        CarDetailedEvaluation evaluation = carDetailedEvaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car evaluation not found with ID: " + id));
        return modelMapper.map(evaluation, CarDetailedEvaluationDTO.class);
    }

    // Update an existing car evaluation
    public CarDetailedEvaluationDTO updateCarEvaluation(Long id, CarDetailedEvaluationDTO evaluationDTO) {
        CarDetailedEvaluation existingEvaluation = carDetailedEvaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car evaluation not found with ID: " + id));

        // Map the evaluation data from the DTO to the existing evaluation object
        modelMapper.map(evaluationDTO, existingEvaluation);
        existingEvaluation.setId(id); // Set the ID to the provided ID for update

        // Save the updated evaluation to the database
        CarDetailedEvaluation updatedEvaluation = carDetailedEvaluationRepository.save(existingEvaluation);

        // Map the updated evaluation to a DTO and return it
        return modelMapper.map(updatedEvaluation, CarDetailedEvaluationDTO.class);
    }

    // Delete a car evaluation by ID
    public void deleteCarEvaluation(Long id) {
        if (!carDetailedEvaluationRepository.existsById(id)) {
            throw new RuntimeException("Car evaluation not found with ID: " + id);
        }
        carDetailedEvaluationRepository.deleteById(id);
    }
}
