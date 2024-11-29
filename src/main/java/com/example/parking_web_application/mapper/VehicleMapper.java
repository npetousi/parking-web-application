package com.example.parking_web_application.mapper;

import com.example.parking_web_application.dtos.VehicleChangesDto;
import com.example.parking_web_application.dtos.VehicleEnteringDto;
import com.example.parking_web_application.model.Vehicle;

import java.math.BigDecimal;

public class VehicleMapper {
    public static Vehicle toEntity(VehicleEnteringDto vehicleEnteringDtO){
        Vehicle vehicle = new Vehicle();
        vehicle.setPlateNumber(vehicleEnteringDtO.getPlateNumber());
        return  vehicle;
    }

    public static VehicleChangesDto toVehicleChangesDto(BigDecimal changes, boolean canProceed, String message){
        VehicleChangesDto vehicleChangesDto = new VehicleChangesDto(changes, canProceed, message);
        return vehicleChangesDto;
    }
}
