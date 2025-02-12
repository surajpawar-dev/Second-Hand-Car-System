package com.suraj.JWT_App.entity.car_details;

import jakarta.persistence.*;

@Entity
@Table(name = "transmission_type")
public class TransmissionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "trans_type", nullable = false, unique = true)
    private String transType;

    public TransmissionType() {
    }

    public TransmissionType(String transType) {
        this.transType = transType;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}