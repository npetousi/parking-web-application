package com.example.parking_web_application.service;

import com.example.parking_web_application.dtos.*;

public interface VehicleService {
    public ParkedVehiclesDto getParkedVehiclesCount();
    public void saveEnteringVehicle(VehicleEnteringDto vehicleEnteringDto);
    public ParkingTicketDto calculateParkingTicket(String plateNumber);
    public VehicleChangesDto processPayment(PaymentRequestDto paymentRequestDto);

}
