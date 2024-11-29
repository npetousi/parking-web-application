package com.example.parking_web_application.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ParkingTicketDto {
    private String plateNumber;

    private BigDecimal parkingTicket;

    private LocalDateTime exactTimeOfCalculatingParkingTicket;

    public ParkingTicketDto(){}

    public ParkingTicketDto(String plateNumber, BigDecimal parkingTicket, LocalDateTime exactTimeOfCalculatingParkingTicket) {
        this.plateNumber = plateNumber;
        this.parkingTicket = parkingTicket;
        this.exactTimeOfCalculatingParkingTicket = exactTimeOfCalculatingParkingTicket;
    }

    public ParkingTicketDto(BigDecimal parkingTicket) {
        this.parkingTicket = parkingTicket;
    }

    public BigDecimal getParkingTicket() {
        return parkingTicket;
    }

    public void setParkingTicket(BigDecimal parkingTicket) {
        this.parkingTicket = parkingTicket;
    }

    public LocalDateTime getExactTimeOfCalculatingParkingTicket() {
        return exactTimeOfCalculatingParkingTicket;
    }

    public void setExactTimeOfCalculatingParkingTicket(LocalDateTime exactTimeOfCalculatingParkingTicket) {
        this.exactTimeOfCalculatingParkingTicket = exactTimeOfCalculatingParkingTicket;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
}
