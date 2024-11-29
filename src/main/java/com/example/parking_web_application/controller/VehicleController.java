package com.example.parking_web_application.controller;

import com.example.parking_web_application.dtos.*;
import com.example.parking_web_application.exceptions.DuplicatePlateNumberException;
import com.example.parking_web_application.exceptions.InsufficientPaymentException;
import com.example.parking_web_application.exceptions.VehicleNotFoundException;
import com.example.parking_web_application.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/parking")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/parked-vehicles")
    public ResponseEntity<ParkedVehiclesDto> getParkedVehiclesCount(){
        ParkedVehiclesDto parkedVehiclesDto = vehicleService.getParkedVehiclesCount();
        return ResponseEntity.ok(parkedVehiclesDto);
    }


    @PostMapping("/enter")
    public ResponseEntity<String> enteringParkingVehicle(@RequestBody VehicleEnteringDto vehicleEnteringDto ){
        try {
            vehicleService.saveEnteringVehicle(vehicleEnteringDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (DuplicatePlateNumberException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @GetMapping("/ticket")
    public ResponseEntity<?> calculateParkingTicket(@RequestParam String plateNumber){
        try{
            ParkingTicketDto parkingTicketDto = vehicleService.calculateParkingTicket(plateNumber);
            return ResponseEntity.ok(parkingTicketDto);
        }catch (VehicleNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PostMapping(value = "/payment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> processPayment(@RequestBody PaymentRequestDto paymentRequestDto){
        ParkingTicketDto parkingTicketDto = paymentRequestDto.getParkingTicketDto();
        PaymentDto paymentDto = paymentRequestDto.getPaymentDto();
        try {
            VehicleChangesDto vehicleChangesDto = vehicleService.processPayment(paymentRequestDto);
            return ResponseEntity.ok(vehicleChangesDto);
        }catch(InsufficientPaymentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (IllegalArgumentException e){
            VehicleChangesDto vehicleChangesDto = new VehicleChangesDto();
            vehicleChangesDto.setCanProceed(false);
            vehicleChangesDto.setMessage(e.getMessage());
            vehicleChangesDto.setChanges(BigDecimal.ZERO);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(vehicleChangesDto);
        }
    }

}
