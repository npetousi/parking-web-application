package com.example.parking_web_application.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "time_entered")
    private LocalDateTime timeEntered;

    public Vehicle(){}

    public Vehicle(String plateNumber, LocalDateTime timeEntered) {
        this.plateNumber = plateNumber;
        this.timeEntered = timeEntered;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber (String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public LocalDateTime getTimeEntered() {
        return timeEntered;
    }

    public void setTimeEntered (LocalDateTime timeEntered) {
        this.timeEntered = timeEntered;
    }
}
