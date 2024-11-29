package com.example.parking_web_application.dtos;

public class ParkedVehiclesDto {
    private Integer parkedVehicles;

    public ParkedVehiclesDto(){}

    public ParkedVehiclesDto(Integer parkedVehicles) {
        this.parkedVehicles = parkedVehicles;
    }

    public int getParkedVehicles() {
        return parkedVehicles;
    }

    public void setParkedVehicles(Integer parkedVehicles) {
        this.parkedVehicles = parkedVehicles;
    }
}
