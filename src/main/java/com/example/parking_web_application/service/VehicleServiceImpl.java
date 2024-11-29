package com.example.parking_web_application.service;

import com.example.parking_web_application.dtos.*;
import com.example.parking_web_application.exceptions.DuplicatePlateNumberException;
import com.example.parking_web_application.exceptions.VehicleNotFoundException;
import com.example.parking_web_application.mapper.VehicleMapper;
import com.example.parking_web_application.model.Vehicle;
import com.example.parking_web_application.repository.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private final VehicleRepo vehicleRepo;

    public VehicleServiceImpl(VehicleRepo vehicleRepo){
        this.vehicleRepo = vehicleRepo;
    }

    @Override
    public ParkedVehiclesDto getParkedVehiclesCount() {
        int numberOfParkedVehicles = (int) vehicleRepo.count();
        return new ParkedVehiclesDto(numberOfParkedVehicles);
    }

    @Override
    public void saveEnteringVehicle(VehicleEnteringDto vehicleEnteringDto) {
        if (vehicleRepo.findById(vehicleEnteringDto.getPlateNumber()).isPresent()){
            throw new DuplicatePlateNumberException("Vehicle with plate number "
                         + vehicleEnteringDto.getPlateNumber() + " already exists in the parking lot."
            );
        }
        Vehicle vehicle = VehicleMapper.toEntity(vehicleEnteringDto);
        vehicle.setTimeEntered(LocalDateTime.now());
        vehicleRepo.save(vehicle);
    }

    @Override
    public ParkingTicketDto calculateParkingTicket(String plateNumber) {
        Vehicle vehicle=vehicleRepo.findById(plateNumber).orElseThrow(
                ()-> new VehicleNotFoundException("Vehicle with plate number "
                        + plateNumber + " not found.")
        );

        LocalDateTime checkOutTime = LocalDateTime.now();
        LocalDateTime checkInTime = vehicle.getTimeEntered();
        long minutesParked=Duration.between(checkInTime,checkOutTime).toMinutes();


        BigDecimal timeParked = BigDecimal.valueOf(minutesParked);
        BigDecimal parkingTicket = calculateParkingTicketHelper(timeParked);

        LocalDateTime dateTimeCalculatingParkingTicket = LocalDateTime.now();
        return new ParkingTicketDto(plateNumber, parkingTicket, dateTimeCalculatingParkingTicket);
    }


    private BigDecimal calculateParkingTicketHelper(BigDecimal timeParked){
        BigDecimal ratePerMin = BigDecimal.valueOf(0.05);
        return ratePerMin.multiply(timeParked);
    }

    @Override
    public VehicleChangesDto processPayment(PaymentRequestDto paymentRequestDto) {
        LocalDateTime dateTimeParkingTicket = paymentRequestDto.getParkingTicketDto().getExactTimeOfCalculatingParkingTicket();

        LocalDateTime paymentTime = LocalDateTime.now();

        long minutesDifference = Duration.between(dateTimeParkingTicket,paymentTime).toMinutes();

        if (minutesDifference > 1){
            throw new IllegalArgumentException("Payment must be made within 1 minute " +
                                            "of calculating the parking ticket.");
        }

        BigDecimal parkingTicket = paymentRequestDto.getParkingTicketDto().getParkingTicket();
        BigDecimal paidAmount = paymentRequestDto.getPaymentDto().getPaidAmount();

        if (paidAmount.compareTo(parkingTicket) < 0){
            throw new IllegalArgumentException("Insufficient payment amount. Please provide more funds.");
        }

        BigDecimal vehicleChanges = paidAmount.subtract(parkingTicket);
        deleteExitingVehicle(paymentRequestDto.getParkingTicketDto().getPlateNumber());

        return VehicleMapper.toVehicleChangesDto(vehicleChanges,true,
                "Payment successful. Change amount: " + vehicleChanges + " euros."
        );

    }

    private void deleteExitingVehicle(String plateNumber){
        vehicleRepo.deleteById(plateNumber);
    }
}

