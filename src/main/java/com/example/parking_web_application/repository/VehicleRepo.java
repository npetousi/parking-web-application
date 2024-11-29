package com.example.parking_web_application.repository;

import com.example.parking_web_application.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle , String> {
}
