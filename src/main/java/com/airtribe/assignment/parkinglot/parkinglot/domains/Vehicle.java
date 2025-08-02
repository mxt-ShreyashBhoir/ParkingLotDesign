package com.airtribe.assignment.parkinglot.parkinglot.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

/**
 * Represents a vehicle entering the parking lot.
 * Registered with a unique ID and vehicle type (motorcycle, car, bus).
 */
@Entity
public class Vehicle {
    @Id
    private String vehicleRegistration;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    public String getVehicleRegistration() {
        return vehicleRegistration;
    }

    public void setVehicleRegistration(String vehicleRegistration) {
        this.vehicleRegistration = vehicleRegistration;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }


}
