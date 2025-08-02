package com.airtribe.assignment.parkinglot.parkinglot.repositories;

import com.airtribe.assignment.parkinglot.parkinglot.domains.ParkingFloor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingFloorRepository  extends JpaRepository<ParkingFloor, Long> {
    // Additional query methods can be defined here if needed
}
