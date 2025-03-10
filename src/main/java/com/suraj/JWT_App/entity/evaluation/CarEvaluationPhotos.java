package com.suraj.JWT_App.entity.evaluation;

import jakarta.persistence.*;

@Entity
@Table(name = "car_evaluation_photos")
public class CarEvaluationPhotos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "photo_url", nullable = false)
    private String photoUrl;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "car_detailed_evaluation_id")
    private CarDetailedEvaluation carDetailedEvaluation;

    public CarDetailedEvaluation getCarDetailedEvaluation() {
        return carDetailedEvaluation;
    }

    public void setCarDetailedEvaluation(CarDetailedEvaluation carDetailedEvaluation) {
        this.carDetailedEvaluation = carDetailedEvaluation;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}