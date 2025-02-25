package com.suraj.JWT_App.entity.evaluation;

import jakarta.persistence.*;

@Entity
@Table(name = "car_detailed_evaluation")
public class CarDetailedEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // Vehicle Details
    @Column(name = "make", nullable = false)
    private String make;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "variant")
    private String variant;

    @Column(name = "registration_number", unique = true)
    private String registrationNumber;

    @Column(name = "chassis_number", unique = true)
    private String chassisNumber;

    // Ownership & Papers
    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "owner_serial_number")
    private int ownerSerialNumber;

    @Column(name = "insurance_valid", nullable = false)
    private boolean insuranceValid;

    @Column(name = "pollution_certificate_valid", nullable = false)
    private boolean pollutionCertificateValid;

    // Car Condition - Exterior
    @Column(name = "scratches")
    private boolean scratches;

    @Column(name = "dents")
    private boolean dents;

    @Column(name = "paint_condition")
    private String paintCondition; // Good, Fair, Poor

    @Column(name = "windshield_condition")
    private String windshieldCondition; // Cracked, Good, Scratched

    // Car Condition - Interior
    @Column(name = "seat_condition")
    private String seatCondition; // Good, Torn, Stained

    @Column(name = "dashboard_condition")
    private String dashboardCondition; // Intact, Damaged

    @Column(name = "ac_working")
    private boolean acWorking;

    // Car Condition - Mechanical
    @Column(name = "engine_health")
    private String engineHealth; // Good, Minor Issues, Needs Repair

    @Column(name = "brake_condition")
    private String brakeCondition; // Good, Worn Out

    @Column(name = "suspension_condition")
    private String suspensionCondition; // Good, Needs Repair

    @Column(name = "battery_health")
    private String batteryHealth; // New, Weak, Needs Replacement

    // Tyre Condition
    @Column(name = "front_tyre_condition")
    private String frontTyreCondition; // Good, Worn Out

    @Column(name = "rear_tyre_condition")
    private String rearTyreCondition; // Good, Worn Out

    // Test Drive Feedback
    @Column(name = "steering_response")
    private String steeringResponse; // Smooth, Hard, Vibrates

    @Column(name = "noise_issues")
    private boolean noiseIssues; // Any unusual noise?

    @Column(name = "transmission_check")
    private String transmissionCheck; // Smooth, Slipping, Delayed

    // Fuel & Mileage Details
    @Column(name = "fuel_type", nullable = false)
    private String fuelType;

    @Column(name = "kms", nullable = false)
    private int kms;

    @Column(name = "year_of_manufacture", nullable = false)
    private int yearOfManufacturing;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getKms() {
        return kms;
    }

    public void setKms(int kms) {
        this.kms = kms;
    }

    public int getYearOfManufacturing() {
        return yearOfManufacturing;
    }

    public void setYearOfManufacturing(int yearOfManufacturing) {
        this.yearOfManufacturing = yearOfManufacturing;
    }

    public boolean isAcWorking() {
        return acWorking;
    }

    public void setAcWorking(boolean acWorking) {
        this.acWorking = acWorking;
    }

    public String getBatteryHealth() {
        return batteryHealth;
    }

    public void setBatteryHealth(String batteryHealth) {
        this.batteryHealth = batteryHealth;
    }

    public String getBrakeCondition() {
        return brakeCondition;
    }

    public void setBrakeCondition(String brakeCondition) {
        this.brakeCondition = brakeCondition;
    }

    public String getDashboardCondition() {
        return dashboardCondition;
    }

    public void setDashboardCondition(String dashboardCondition) {
        this.dashboardCondition = dashboardCondition;
    }

    public boolean isDents() {
        return dents;
    }

    public void setDents(boolean dents) {
        this.dents = dents;
    }

    public String getEngineHealth() {
        return engineHealth;
    }

    public void setEngineHealth(String engineHealth) {
        this.engineHealth = engineHealth;
    }

    public String getFrontTyreCondition() {
        return frontTyreCondition;
    }

    public void setFrontTyreCondition(String frontTyreCondition) {
        this.frontTyreCondition = frontTyreCondition;
    }

    public boolean isInsuranceValid() {
        return insuranceValid;
    }

    public void setInsuranceValid(boolean insuranceValid) {
        this.insuranceValid = insuranceValid;
    }

    public boolean isNoiseIssues() {
        return noiseIssues;
    }

    public void setNoiseIssues(boolean noiseIssues) {
        this.noiseIssues = noiseIssues;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getOwnerSerialNumber() {
        return ownerSerialNumber;
    }

    public void setOwnerSerialNumber(int ownerSerialNumber) {
        this.ownerSerialNumber = ownerSerialNumber;
    }

    public String getPaintCondition() {
        return paintCondition;
    }

    public void setPaintCondition(String paintCondition) {
        this.paintCondition = paintCondition;
    }

    public boolean isPollutionCertificateValid() {
        return pollutionCertificateValid;
    }

    public void setPollutionCertificateValid(boolean pollutionCertificateValid) {
        this.pollutionCertificateValid = pollutionCertificateValid;
    }

    public String getRearTyreCondition() {
        return rearTyreCondition;
    }

    public void setRearTyreCondition(String rearTyreCondition) {
        this.rearTyreCondition = rearTyreCondition;
    }

    public boolean isScratches() {
        return scratches;
    }

    public void setScratches(boolean scratches) {
        this.scratches = scratches;
    }

    public String getSeatCondition() {
        return seatCondition;
    }

    public void setSeatCondition(String seatCondition) {
        this.seatCondition = seatCondition;
    }

    public String getSteeringResponse() {
        return steeringResponse;
    }

    public void setSteeringResponse(String steeringResponse) {
        this.steeringResponse = steeringResponse;
    }

    public String getSuspensionCondition() {
        return suspensionCondition;
    }

    public void setSuspensionCondition(String suspensionCondition) {
        this.suspensionCondition = suspensionCondition;
    }

    public String getTransmissionCheck() {
        return transmissionCheck;
    }

    public void setTransmissionCheck(String transmissionCheck) {
        this.transmissionCheck = transmissionCheck;
    }

    public String getWindshieldCondition() {
        return windshieldCondition;
    }

    public void setWindshieldCondition(String windshieldCondition) {
        this.windshieldCondition = windshieldCondition;
    }

}
