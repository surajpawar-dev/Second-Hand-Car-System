package com.suraj.JWT_App.helper;

import com.suraj.JWT_App.entity.car_details.*;
import com.suraj.JWT_App.payload.CarDetailsDTOs.*;
import com.suraj.JWT_App.repository.CarDetailsRepos.*;
import org.springframework.stereotype.Component;

@Component
public class EntityFinderHelper {
    // Helper class for finding entities using their IDs and Names

    private final BrandRepository brandRepository;
    private final ColorRepository colorRepository;
    private final FuelTypeRepository fuelTypeRepository;
    private final ModelRepository modelRepository;
    private final TransmissionTypeRepository typeRepository;
    private final YearOfManufactureRepository yearRepository;

    public EntityFinderHelper(BrandRepository brandRepository, ColorRepository colorRepository, FuelTypeRepository fuelTypeRepository, ModelRepository modelRepository, TransmissionTypeRepository typeRepository, YearOfManufactureRepository yearRepository) {
        this.brandRepository = brandRepository;
        this.colorRepository = colorRepository;
        this.fuelTypeRepository = fuelTypeRepository;
        this.modelRepository = modelRepository;
        this.typeRepository = typeRepository;
        this.yearRepository = yearRepository;
    }

    public Brand findOrCreateBrand(BrandDTO brandDTO) {
        if (brandDTO == null) return null;

        return brandDTO.getId() != null
                ? brandRepository.findById(brandDTO.getId())
                .orElseThrow(() -> new RuntimeException("Brand not found"))

                : brandRepository.findByBrandName(brandDTO.getBrandName())
                .orElseGet(() -> brandRepository.save(new Brand(brandDTO.getBrandName())));
    }

    public Color findOrCreateColor(ColorDTO colorDTO) {
        if (colorDTO == null) return null;

        return colorDTO.getId() != null
                ? colorRepository.findById(colorDTO.getId())
                .orElseThrow(() -> new RuntimeException("Color not found"))

                : colorRepository.findByColor(colorDTO.getColor())
                .orElseGet(() -> colorRepository.save(new Color(colorDTO.getColor())));
    }

    public FuelType findOrCreateFuelType(FuelTypeDTO fuelTypeDTO) {
        if (fuelTypeDTO == null) return null;

        if (fuelTypeDTO.getId() != null) {
            return fuelTypeRepository.findById(fuelTypeDTO.getId())
                    .orElseThrow(() -> new RuntimeException("FuelType not found"));
        }

        return fuelTypeRepository.findByType(fuelTypeDTO.getType())
                .orElseGet(() -> fuelTypeRepository.save(new FuelType(fuelTypeDTO.getType())));
    }

    public Model findOrCreateModel(ModelDTO modelDTO) {
        if (modelDTO == null) return null;

        if (modelDTO.getId() != null) {
            return modelRepository.findById(modelDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Model Not Found"));
        }

        return modelRepository.findByModelName(modelDTO.getModelName())
                .orElseGet(() -> modelRepository.save(new Model(modelDTO.getModelName())));
    }

    public TransmissionType findOrCreateTransmissionType(TransmissionTypeDTO typeDTO) {
        if (typeDTO == null) return null;

        if (typeDTO.getId() != null) {
            return typeRepository.findById(typeDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Transmission Not found"));
        }
        return typeRepository.findByTransType(typeDTO.getTransType())
                .orElseGet(() -> typeRepository.save(new TransmissionType(typeDTO.getTransType())));
    }

    public YearOfManufacture findOrCreateYear(YearOfManufactureDTO yearDTO) {
        if (yearDTO == null)
            return null;

        if (yearDTO.getId() != null) {
            return yearRepository.findById(yearDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Year Not Found"));
        }

        return yearRepository.findByYear(yearDTO.getYear())
                .orElseGet(() -> yearRepository.save(new YearOfManufacture(yearDTO.getYear())));
    }
}
