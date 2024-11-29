package com.example.parking_web_application.dtos;

import java.time.LocalDateTime;

public class VehicleEnteringDto {
    private String plateNumber;

    public VehicleEnteringDto() {
    }

    public VehicleEnteringDto(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }


}
