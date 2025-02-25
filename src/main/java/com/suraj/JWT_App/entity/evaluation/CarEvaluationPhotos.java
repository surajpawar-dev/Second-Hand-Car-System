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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_evaluation_id", nullable = false)
    private CarDetailedEvaluation carEvaluation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}