package com.suraj.JWT_App.payload.Evaluation.visitDTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class VisitRequestDTO {
    private Long customerId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate preferredDate;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime preferredTime;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDate getPreferredDate() {
        return preferredDate;
    }

    public void setPreferredDate(LocalDate preferredDate) {
        this.preferredDate = preferredDate;
    }

    public LocalTime getPreferredTime() {
        return preferredTime;
    }

    public void setPreferredTime(LocalTime preferredTime) {
        this.preferredTime = preferredTime;
    }
}
