package com.airtribe.assignment.parkinglot.parkinglot.domains;

/**
 * Strategy interface for parking space assignment.
 */
public interface ParkingStrategy {
    ParkingSpace assignParkingSpot(VehicleType vehicleType);
    boolean supports(EntryGate.ParkingStrategyType type);
}
