package com.suraj.JWT_App.payload.CarDetailsDTOs;

import java.util.List;
import java.util.Set;

public class BrandDTO {
    private Long id;
    private String brandName;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }


}