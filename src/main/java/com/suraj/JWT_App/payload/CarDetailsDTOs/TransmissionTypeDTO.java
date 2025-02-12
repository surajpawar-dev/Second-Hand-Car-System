package com.suraj.JWT_App.payload.CarDetailsDTOs;


public class TransmissionTypeDTO {
    private Long id;
    private String transType;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }
}
